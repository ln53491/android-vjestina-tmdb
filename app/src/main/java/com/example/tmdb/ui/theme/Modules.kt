package com.example.tmdb.ui.theme

import org.koin.androidx.compose.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Database
import com.example.tmdb.data.Client
import com.example.tmdb.repository.*
import com.example.tmdb.repository.MovieApiImpl
import com.example.tmdb.repository.MovieRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        FavoritesViewModel(get())
    }

}

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(MovieApiImpl(Client().httpClient), Database())
    }
    single<MovieApi> {
        MovieApiImpl(Client().httpClient)
    }
    single {
        Client().httpClient
    }
}

//val repositoryModule = module {
//    single<MovieRepository> {
//        MovieRepositoryImpl(MovieApiImpl())
//    }
//    single { MovieDatabase() }
//}

//val apiModule = module {
//    single { MovieApiImpl() }
//}