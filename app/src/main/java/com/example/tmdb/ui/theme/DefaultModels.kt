package com.example.tmdb.ui.theme

import com.example.tmdb.R

var titleImage = R.drawable.title

var favoritesMap = mutableMapOf(
    R.drawable.ironman1 to true,
    R.drawable.gattaca to false,
    R.drawable.lionking to true,
    R.drawable.puppylove to true,
    R.drawable.junglebeat to false,
    R.drawable.civilwar to false,
    R.drawable.ironman2 to true,
    R.drawable.godzilla to true
)

var defaultHome = HomeScreenModel(
    popularTab = listOf(
        R.drawable.ironman1,
        R.drawable.gattaca,
        R.drawable.lionking
    ),
    freetowatchTab = listOf(
        R.drawable.puppylove,
        R.drawable.junglebeat,
        R.drawable.civilwar
    ),
    trendingTab = listOf(
        R.drawable.ironman2,
        R.drawable.godzilla,
        R.drawable.gattaca
    )
)

var defaultMovie = MovieScreenModel(
    title = "Iron Man",
    releaseYear = 2008,
    releaseDate = "05/02/2008 (US)",
    genres = "Action, Adventure, Science Fiction",
    duration = "2h 6m",
    overview = "After being held captive in an Afghan cave, " +
                "billionaire engineer Tony Stark creates a unique " +
                "weaponized suit of armor to fight evil.",
    userScore = 76,
    star = 0,
    background = R.drawable.ironman_bg,
    infoPeople = listOf(
        Pair("Don Heck", "Characters"),
        Pair("Jack Kirby", "Characters"),
        Pair("Jon Favreau", "Director"),
        Pair("Don Heck", "Screenplay"),
        Pair("Jack Marcum", "Screenplay"),
        Pair("Matt Holloway", "Screenplay")),
    cast = listOf(
        Triple(R.drawable.ironman_actor_1,"Robert Downey Jr.", "Tony Stark"),
        Triple(R.drawable.ironman_actor_2,"Terrence Howard", "James Rhodes"),
        Triple(R.drawable.ironman_actor_3,"Jeff Bridges", "Obadiah")),
    reviewNumber = 5,
    discussionNumber = 4,
    recommendations = listOf(
        R.drawable.ironman2,
        R.drawable.godzilla,
        R.drawable.puppylove
    ),
    discussions = listOf(),
    reviews = listOf(
        Review(
            author = "The Peruvian Post",
            date = "Februrary 17, 2020",
            image = R.drawable.author1,
            text = "When director Jon Favreau and Sarah Halley cast Robert Downey Jr, they " +
                    "glimpsed something magnificent: a more-than-skilled actor who faultlessly " +
                    "portrayed the role of Tony Stark. Despite Favreau's initial decision in " +
                    "choosing a fresh face, he ended up delighted due to his charismatic, natural " +
                    "and comfortable attitude. He did not realise it yet, but he was moulding " +
                    "with the right measures a whole superhero cinematic universe which lasted " +
                    "until today and still goes for more.\n" +
                    "\n" +
                    "The filmmakers took the proper time to introduce a character whose " +
                    "production was undecided since New Line Pictures argu... read the rest."
        )
    )
)


data class HomeScreenModel(
    val popularTab: List<Int>,
    val freetowatchTab: List<Int>,
    val trendingTab: List<Int>
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