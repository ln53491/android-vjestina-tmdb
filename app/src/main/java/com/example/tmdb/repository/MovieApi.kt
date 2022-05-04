package com.example.tmdb.repository

interface MovieApi {

    fun getPopularMovies(): List<Movie>
    fun getFreeToWatchMovies(): List<Movie>
    fun getTrendingMovies(): List<Movie>

}

internal class MovieApiImpl: MovieApi {

    private val database: MovieDatabase = MovieDatabase()

    override fun getPopularMovies(): List<Movie> {
        return (database.movies.filter { it.popular == true })
    }

    override fun getFreeToWatchMovies(): List<Movie> = (
        database.movies.filter { it.freeToWatch == true }
    )

    override fun getTrendingMovies(): List<Movie> = (
        database.movies.filter { it.trending == true }
    )

}
