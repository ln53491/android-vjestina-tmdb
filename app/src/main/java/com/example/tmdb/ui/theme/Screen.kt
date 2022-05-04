package com.example.tmdb.ui.theme

sealed class Screen() {
    object HomeScreen : Screen()
    object FavoritesScreen : Screen()
    object MovieScreen : Screen()
}