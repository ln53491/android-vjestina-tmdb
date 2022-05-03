package com.example.tmdb.ui.theme

sealed class Screen(val route: String){
    object Home : Screen("home_screen")
    object Favorites : Screen("favorites_screen")
    object Movie : Screen("movie_screen")
}