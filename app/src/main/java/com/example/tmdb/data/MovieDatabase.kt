package com.example.tmdb.repository

import com.example.tmdb.R

class MovieDatabase{

    fun setFavorite(movie : Movie) {
        favorites.add(movie)
    }

    fun removeFavorite(movie : Movie) {
        favorites.remove(movie)
    }

    val favorites: MutableList<Movie> = mutableListOf(
        Movie(
            id = 1,
            image = R.drawable.ironman1,
            popular = true,
            freeToWatch = false,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 2,
            image = R.drawable.gattaca,
            popular = true,
            freeToWatch = false,
            trending = true,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 3,
            image = R.drawable.lionking,
            popular = true,
            freeToWatch = false,
            trending = false,
            movieScreenModel = defaultMovie
        )
    )

    var movies: List<Movie> = listOf(
        Movie(
            id = 1,
            image = R.drawable.ironman1,
            popular = true,
            freeToWatch = false,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 2,
            image = R.drawable.gattaca,
            popular = true,
            freeToWatch = false,
            trending = true,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 3,
            image = R.drawable.lionking,
            popular = true,
            freeToWatch = false,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 4,
            image = R.drawable.puppylove,
            popular = false,
            freeToWatch = true,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 5,
            image = R.drawable.junglebeat,
            popular = false,
            freeToWatch = true,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 6,
            image = R.drawable.civilwar,
            popular = false,
            freeToWatch = true,
            trending = false,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 7,
            image = R.drawable.ironman2,
            popular = false,
            freeToWatch = false,
            trending = true,
            movieScreenModel = defaultMovie
        ),
        Movie(
            id = 8,
            image = R.drawable.godzilla,
            popular = false,
            freeToWatch = false,
            trending = true,
            movieScreenModel = defaultMovie
        )
    )
}