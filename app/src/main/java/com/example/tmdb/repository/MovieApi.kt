package com.example.tmdb.repository

interface MovieApi {

    fun getPopularMovies(): MovieResponse
    fun getFreeToWatchMovies(): MovieResponse
    fun getTrendingMovies(): MovieResponse
    fun getFavoriteMovies(): MovieResponse
    fun getFavoriteById(id: Int): Movie
    fun setFavorite(id: Int)
    fun removeFavorite(id: Int)

}

internal class MovieApiImpl: MovieApi {

    override fun getPopularMovies(): MovieResponse {
        return MovieResponse(movies.filter { it.popular == true })
    }

    override fun getFreeToWatchMovies(): MovieResponse = MovieResponse(
        movies.filter { it.freeToWatch == true }
    )

    override fun getTrendingMovies(): MovieResponse = MovieResponse(
        movies.filter { it.trending == true }
    )

    override fun getFavoriteMovies(): MovieResponse = MovieResponse(
        movies.filter { it.favorite == true }
    )

    override fun getFavoriteById(id: Int): Movie{
        return movies.filter { it.favorite == true && it.id == id }[0]
    }

    override fun setFavorite(id: Int){
        var movie = movies.filter { it.favorite == true && it.id == id }[0]
        movie.favorite = true
    }

    override fun removeFavorite(id: Int){
        var movie = movies.filter { it.favorite == true && it.id == id }[0]
        movie.favorite = false
    }
}
