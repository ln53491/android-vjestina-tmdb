package com.example.tmdb.repository

import com.example.tmdb.repository.Movie
import kotlinx.coroutines.flow.*

interface MovieRepository {

    //u sklopu izvedbe "lokalne" baze podataka bez zahtjeva za Flow s API-ja,
    //sve funkcije vraćaju obične liste (do daljnjega)
    fun getPopularMovies(): List<Movie>
    fun getFreeToWatchMovies(): List<Movie>
    fun getTrendingMovies(): List<Movie>
    fun getFavoriteMovies(): List<Movie>
    fun getFavoriteById(id: Int): Movie
    fun setFavorite(id: Int)
    fun removeFavorite(id: Int)
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {

    override fun getPopularMovies(): List<Movie> {
        return movieApi.getPopularMovies().getList()
    }

    override fun getFreeToWatchMovies(): List<Movie> {
        return movieApi.getFreeToWatchMovies().getList()
    }

    override fun getTrendingMovies(): List<Movie> {
        return movieApi.getTrendingMovies().getList()
    }

    override fun getFavoriteMovies(): List<Movie> {
        return movieApi.getFavoriteMovies().getList()
    }

    override fun getFavoriteById(id: Int): Movie {
        return movieApi.getFavoriteById(id = id)
    }

    override fun setFavorite(id: Int) {
        return movieApi.setFavorite(id = id)
    }

    override fun removeFavorite(id: Int) {
        return movieApi.removeFavorite(id = id)
    }
}

