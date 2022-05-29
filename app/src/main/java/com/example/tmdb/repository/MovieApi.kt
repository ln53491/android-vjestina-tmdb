package com.example.tmdb.repository

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*

interface MovieApi {
    suspend fun getPopularMovies(): MoviesListResponse
    suspend fun getNowPlayingMovies(): MoviesListResponse
    suspend fun getUpcomingMovies(): MoviesListResponse
    suspend fun getTopRatedMovies(): MoviesListResponse
}

private const val BASE_URL = "https://api.themoviedb.org/3"
private const val API_KEY = "377303d9a8ff15047528752e876dc9cf"

internal class MovieApiImpl(
    private val client: HttpClient
): MovieApi {

    override suspend fun getPopularMovies(): MoviesListResponse =
        client.get("$BASE_URL/movie/popular?api_key=$API_KEY")

    override suspend fun getNowPlayingMovies(): MoviesListResponse =
        client.get("$BASE_URL/movie/now_playing?api_key=$API_KEY")

    override suspend fun getUpcomingMovies(): MoviesListResponse =
        client.get("$BASE_URL/movie/upcoming?api_key=$API_KEY")

    override suspend fun getTopRatedMovies(): MoviesListResponse =
        client.get("$BASE_URL/movie/top_rated?api_key=$API_KEY")
}

//internal class MovieApiImpl: MovieApi {
//
//    private val database: MovieDatabase = MovieDatabase()
//
//    override fun getPopularMovies(): List<Movie> {
//        return (database.movies.filter { it.popular == true })
//    }
//
//    override fun getFreeToWatchMovies(): List<Movie> = (
//            database.movies.filter { it.freeToWatch == true }
//            )
//
//    override fun getTrendingMovies(): List<Movie> = (
//            database.movies.filter { it.trending == true }
//            )
//
//}
