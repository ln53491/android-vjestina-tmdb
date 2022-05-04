package com.example.tmdb.repository

import com.example.tmdb.repository.Movie
import kotlinx.coroutines.flow.*

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getFreeToWatchMovies(): Flow<List<Movie>>
    suspend fun getTrendingMovies(): Flow<List<Movie>>
    suspend fun getFavoriteMovies(): Flow<MutableList<Movie>>
    suspend fun setFavorite(movie: Movie)
    suspend fun removeFavorite(movie: Movie)
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {

    private val database: MovieDatabase = MovieDatabase()

    override suspend fun getPopularMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getPopularMovies())
    }

    override suspend fun getFreeToWatchMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getFreeToWatchMovies())
    }

    override suspend fun getTrendingMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getTrendingMovies())
    }

    override suspend fun getFavoriteMovies(): Flow<MutableList<Movie>> = flow {
        emit(database.favorites)
    }

    override suspend fun setFavorite(movie: Movie){
        database.setFavorite(movie)
    }

    override suspend fun removeFavorite(movie: Movie){
        database.removeFavorite(movie)
    }
}

