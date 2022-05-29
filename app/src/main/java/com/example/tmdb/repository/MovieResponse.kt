package com.example.tmdb.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListResponse(
    @SerialName("results")
    var movies: List<MovieResponse>
)

@Serializable
data class MovieResponse(
    var adult : Boolean,
    var backdrop_path : String,
    var genre_ids : List<Int>,
    var id : Int,
    var original_language : String,
    var original_title : String,
    var overview : String,
    var popularity : Float,
    var poster_path : String,
    var release_date : String,
    var title : String,
    var video : Boolean,
    var vote_average : Float,
    var vote_count : Int
)

//const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
//
//fun MovieResponse.toMovie(isFavorite: Boolean) = MovieNew(
//    id,
//    title,
//    overview,
//    poster_path?.let{"$BASE_IMAGE_URL/&it"},
//    isFavorite = true
//)