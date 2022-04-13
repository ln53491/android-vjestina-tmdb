package com.example.tmdb

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.repository.Movie
import com.example.tmdb.screens.Navigation
import com.example.tmdb.viewmodels.FavoritesViewModel
import com.example.tmdb.viewmodels.HomeViewModel
import com.example.tmdb.viewmodels.MovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.compose.R.layout
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.context.GlobalContext.get

class MainActivity: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {

            val homeViewModel: HomeViewModel by viewModel()
            val favoritesViewModel: FavoritesViewModel by viewModel()
            val movieViewModel: MovieViewModel by viewModel()

//            Navigation(true ,homeViewModel, favoritesViewModel, movieViewModel)
            Navigation(true)
        }

    }
}