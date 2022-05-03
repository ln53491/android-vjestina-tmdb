package com.example.tmdb.repository

import org.koin.androidx.compose.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tmdb.viewmodels.FavoritesViewModel
import com.example.tmdb.viewmodels.HomeViewModel
import com.example.tmdb.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        FavoritesViewModel(get())
    }
    viewModel {
        MovieViewModel(get())
    }
}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val apiModule = module {
    single<MovieApi> { MovieApiImpl() }
}
