# Movie Recommendation System

A Jupyter notebook project using MovieLens `ml-latest-small`. It explores ratings and movie metadata, cleans titles, genres, and tags, then recommends similar movies with TF-IDF features and cosine distance. A small command-line interface uses the same modeling approach.

## Setup

From this folder, install the dependencies and register a notebook kernel:

```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
python -m pip install -r requirements.txt
python -m ipykernel install --user --name movie-recommendation --display-name "Python (Movie Recommendation)"
```

Open `movie_recommendation_system.ipynb` and select the `Python (Movie Recommendation)` kernel. Run the cells from top to bottom. The first data cell downloads and caches the public MovieLens dataset under `data/`.

## Run the CLI

```powershell
python recommender.py "Toy Story (1995)" --count 5
```

The first run downloads MovieLens if it is not cached. Titles are matched exactly, ignoring capitalization.

## Run tests

```powershell
python -m unittest -v
```

## Method and evaluation

The notebook covers dataset sizes, missing values, duplicates, rating distributions, genre counts, and top-rated movies with at least 50 ratings. Movie title text, genres, and aggregated user tags are combined, vectorized with TF-IDF, and ranked by cosine similarity. The notebook includes three sample queries and assertions. These examples are qualitative checks; they do not replace a held-out user-preference ranking evaluation.