import unittest

import pandas as pd

from recommender import ContentRecommender


class ContentRecommenderTests(unittest.TestCase):
    def setUp(self):
        movies = pd.DataFrame(
            {
                "movieId": [1, 2, 3, 4],
                "title": [
                    "Space Quest (2001)",
                    "Galaxy Trip (2003)",
                    "Quiet Home (1998)",
                    "Another Space Story (2004)",
                ],
                "genres": ["Adventure|Sci-Fi", "Adventure|Sci-Fi", "Drama", "Adventure|Sci-Fi"],
            }
        )
        tags = pd.DataFrame(
            {
                "movieId": [1, 2, 3, 4],
                "tag": ["starship exploration", "starship space exploration planets", "family relationships", "outer space travel"],
            }
        )
        self.recommender = ContentRecommender(movies, tags)

    def test_recommends_a_related_movie_first(self):
        results = self.recommender.recommend("Space Quest (2001)", n=2)
        self.assertEqual(results.iloc[0]["title"], "Galaxy Trip (2003)")
        self.assertNotIn("Space Quest (2001)", results["title"].tolist())
        self.assertTrue(results["similarity"].between(0, 1).all())

    def test_title_lookup_is_case_insensitive(self):
        results = self.recommender.recommend("space quest (2001)", n=1)
        self.assertEqual(len(results), 1)

    def test_unknown_title_suggests_possible_matches(self):
        with self.assertRaisesRegex(KeyError, "Possible matches"):
            self.recommender.recommend("Space Quest")

    def test_rejects_nonpositive_result_count(self):
        with self.assertRaises(ValueError):
            self.recommender.recommend("Space Quest (2001)", n=0)


if __name__ == "__main__":
    unittest.main()