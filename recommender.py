"""Content-based movie recommendations using MovieLens metadata."""

from __future__ import annotations

import argparse
import re
import shutil
import ssl
from pathlib import Path, PurePosixPath
from urllib.request import urlopen
from zipfile import ZipFile

import certifi
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.neighbors import NearestNeighbors

DATA_URL = "https://files.grouplens.org/datasets/movielens/ml-latest-small.zip"
REQUIRED_FILES = {"movies.csv", "tags.csv"}


def load_movielens(data_dir: Path = Path("data/ml-latest-small")) -> tuple[pd.DataFrame, pd.DataFrame]:
    """Download/cache MovieLens and load its movie and tag metadata."""
    data_dir = Path(data_dir)
    data_dir.mkdir(parents=True, exist_ok=True)
    archive_path = data_dir.parent / "ml-latest-small.zip"
    if not archive_path.exists():
        print(f"Downloading MovieLens to {archive_path}...")
        with urlopen(DATA_URL, context=ssl.create_default_context(cafile=certifi.where())) as response:
            with archive_path.open("wb") as target:
                shutil.copyfileobj(response, target)

    with ZipFile(archive_path) as archive:
        members = {}
        for member in archive.infolist():
            path = PurePosixPath(member.filename)
            if path.name in REQUIRED_FILES:
                if path.is_absolute() or ".." in path.parts:
                    raise ValueError(f"Unsafe archive path: {member.filename}")
                members[path.name] = member
        missing = REQUIRED_FILES - members.keys()
        if missing:
            raise ValueError(f"MovieLens archive is missing: {sorted(missing)}")
        for filename, member in members.items():
            destination = data_dir / filename
            if not destination.exists():
                with archive.open(member) as source, destination.open("wb") as target:
                    shutil.copyfileobj(source, target)

    return pd.read_csv(data_dir / "movies.csv"), pd.read_csv(data_dir / "tags.csv")


class ContentRecommender:
    """Rank movies by cosine similarity of their title, genre, and tag text."""

    def __init__(self, movies: pd.DataFrame, tags: pd.DataFrame) -> None:
        cleaned = movies.drop_duplicates(subset="movieId").copy()
        cleaned["title"] = cleaned["title"].fillna("").astype(str).str.strip()
        cleaned["genres"] = (
            cleaned["genres"].fillna("").astype(str).str.replace("|", " ", regex=False)
            .str.lower().str.strip()
        )
        cleaned["title_text"] = (
            cleaned["title"].str.replace(r"\s*\(\d{4}\)\s*$", "", regex=True)
            .str.lower().str.replace(r"[^a-z0-9 ]+", " ", regex=True)
            .str.replace(r"\s+", " ", regex=True).str.strip()
        )

        clean_tags = tags.drop_duplicates().copy()
        clean_tags["tag"] = (
            clean_tags["tag"].fillna("").astype(str).str.lower()
            .str.replace(r"[^a-z0-9 ]+", " ", regex=True)
            .str.replace(r"\s+", " ", regex=True).str.strip()
        )
        tag_text = clean_tags.groupby("movieId")["tag"].agg(" ".join)
        cleaned["tag_text"] = cleaned["movieId"].map(tag_text).fillna("")
        cleaned["features"] = (
            cleaned["title_text"] + " " + cleaned["genres"] + " " + cleaned["tag_text"]
        ).str.strip()
        if cleaned.empty:
            raise ValueError("At least one movie is required to fit the recommender.")

        self.movies = cleaned.reset_index(drop=True)
        self.vectorizer = TfidfVectorizer(stop_words="english", ngram_range=(1, 2))
        self.feature_matrix = self.vectorizer.fit_transform(self.movies["features"])
        self.neighbors = NearestNeighbors(metric="cosine", algorithm="brute").fit(self.feature_matrix)
        self.title_to_index = {
            title.casefold(): index for index, title in enumerate(self.movies["title"])
        }

    def recommend(self, title: str, n: int = 10) -> pd.DataFrame:
        """Return the closest titles and cosine similarity scores."""
        if n < 1:
            raise ValueError("n must be at least 1")
        key = str(title).strip().casefold()
        if key not in self.title_to_index:
            matches = self.movies.loc[
                self.movies["title"].str.casefold().str.contains(re.escape(str(title).strip()), na=False),
                "title",
            ].head(8).tolist()
            raise KeyError(f"Movie title not found: {title!r}. Possible matches: {matches}")

        row_index = self.title_to_index[key]
        count = min(n + 1, len(self.movies))
        distances, indices = self.neighbors.kneighbors(
            self.feature_matrix[row_index], n_neighbors=count
        )
        ranked = [
            (index, 1 - distance)
            for index, distance in zip(indices[0], distances[0])
            if index != row_index
        ][:n]
        results = self.movies.iloc[[index for index, _ in ranked]][["title", "genres"]].copy()
        results.insert(0, "similarity", [score for _, score in ranked])
        return results.reset_index(drop=True)


def main() -> None:
    parser = argparse.ArgumentParser(description="Recommend movies similar to a MovieLens title.")
    parser.add_argument("title", help='Exact movie title, e.g. "Toy Story (1995)"')
    parser.add_argument("-n", "--count", type=int, default=10, help="number of recommendations")
    args = parser.parse_args()

    movies, tags = load_movielens()
    recommender = ContentRecommender(movies, tags)
    print(recommender.recommend(args.title, n=args.count).to_string(index=False))


if __name__ == "__main__":
    main()