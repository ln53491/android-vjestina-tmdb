package com.example.tmdb.data

sealed class Screen(val route: String){
    object Home : Screen("home_screen")
    object Favorites : Screen("favorites_screen")
    object Movie : Screen("movie_screen")
}
