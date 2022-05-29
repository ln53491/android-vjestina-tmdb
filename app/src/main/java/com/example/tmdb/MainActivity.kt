package com.example.tmdb

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import com.example.tmdb.repository.defaultMovie
import com.example.tmdb.screens.FavoritesScreen
import com.example.tmdb.ui.theme.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.compose.getViewModel

class MainActivity: ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class,
        androidx.compose.foundation.ExperimentalFoundationApi::class
    )
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeViewModel = getViewModel()
            val favoritesViewModel: FavoritesViewModel by viewModel()

            when (Navigator.currentScreen) {
                Screen.HomeScreen -> HomeScreen(homeViewModel, favoritesViewModel)
                Screen.FavoritesScreen -> FavoritesScreen(favoritesViewModel, homeViewModel)
                Screen.MovieScreen -> MovieScreen(homeViewModel, defaultMovie)
            }
        }

    }
}