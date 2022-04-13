package com.example.tmdb.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb.data.Screen
import com.example.tmdb.repository.MovieScreenModel
import com.example.tmdb.repository.defaultMovie
import com.example.tmdb.screens.FavoritesScreen
import com.example.tmdb.viewmodels.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun Navigation(
    preview: Boolean = false,
//    homeViewModel: HomeViewModel,
//    favoritesViewModel: FavoritesViewModel,
//    movieViewModel: MovieViewModel
){
    val navController = rememberNavController()
    if (preview) {
//        HomeScreen(navController, homeViewModel)
        HomeScreen(navController = navController)
    } else {
        NavHost(navController = navController, startDestination = Screen.Home.route){

            composable(route = Screen.Home.route){
//                HomeScreen(navController = navController, homeViewModel)
                HomeScreen(navController = navController)
            }
            composable(route = Screen.Favorites.route){
                FavoritesScreen(navController = navController)
            }
            composable(route = Screen.Movie.route){
                MovieScreen(navController = navController, defaultMovie)
            }

        }
    }
}

fun <K, V> getKeys(map: Map<K, V>, target: V): List<K>? {
    return map.keys.filter { key: K -> target == map[key] }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun NavigatorPreview(){
    Navigation(true)
}