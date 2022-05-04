package com.example.tmdb.repository

data class Movie(
    val id: Int,
    var image: Int,
    var popular: Boolean = false,
    var freeToWatch: Boolean = false,
    var trending: Boolean = false,
    var favorite: Boolean = false,
    var movieScreenModel: MovieScreenModel
)

data class MovieScreenModel(
    val title: String,
    val releaseYear: Int,
    val releaseDate: String,
    val genres: String,
    val duration: String,
    val userScore: Int,
    val star: Int,
    val background: Int,
    val overview: String,
    val infoPeople: List<Pair<String, String>>,
    val cast: List<Triple<Int, String, String>>,
    val reviewNumber: Int,
    val reviews: List<Review>,
    val discussionNumber: Int,
    val recommendations: List<Int>,
    val discussions: List<Discussion>
)

data class Review(
    val author: String,
    val date: String,
    val image: Int,
    val text: String)

data class Discussion(
    val author: String,
    val date: String,
    val image: Int,
    val text: String)
