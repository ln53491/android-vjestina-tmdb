package com.example.tmdb.repository

import com.example.tmdb.repository.Movie

class MovieResponse(
    movieList: List<Movie>,
) {
    private val movies = movieList

    fun getList(): List<Movie> {
        return movies
    }
}
