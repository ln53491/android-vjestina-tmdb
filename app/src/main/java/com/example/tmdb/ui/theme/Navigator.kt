package com.example.tmdb.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object Navigator {
    var currentScreen: Screen by mutableStateOf(Screen.HomeScreen)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}