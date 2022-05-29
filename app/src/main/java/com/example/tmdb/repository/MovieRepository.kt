package com.example.tmdb.repository

import kotlinx.coroutines.flow.*

interface MovieRepository {


//    returnovi su flow, emitaju se s delayom
    //api samo returna movieresponse
    suspend fun getPopularMovies(): Flow<List<MovieResponse>>
    suspend fun getNowPlayingMovies(): Flow<List<MovieResponse>>
    suspend fun getUpcomingMovies(): Flow<List<MovieResponse>>
    suspend fun getFavoriteMovies(): Flow<MutableList<MovieResponse>>
    suspend fun getTopRatedMovies(): Flow<List<MovieResponse>>
    suspend fun setFavorite(movie: MovieResponse)
    suspend fun removeFavorite(movie: MovieResponse)
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val database : Database
): MovieRepository {

    override suspend fun getPopularMovies() : Flow<List<MovieResponse>> = flow {
        emit(movieApi.getPopularMovies().movies)
    }

    override suspend fun getNowPlayingMovies() : Flow<List<MovieResponse>> = flow {
        emit(movieApi.getNowPlayingMovies().movies)
    }

    override suspend fun getUpcomingMovies() : Flow<List<MovieResponse>> = flow {
        emit(movieApi.getUpcomingMovies().movies)
    }

    override suspend fun getTopRatedMovies() : Flow<List<MovieResponse>> = flow {
        emit(movieApi.getTopRatedMovies().movies)
    }

    override suspend fun getFavoriteMovies(): Flow<MutableList<MovieResponse>> = flow {
        emit(database.favorites)
    }

    override suspend fun setFavorite(movie: MovieResponse){
        database.setFavorite(movie)
    }

    override suspend fun removeFavorite(movie: MovieResponse){
        database.removeFavorite(movie)
    }
}

