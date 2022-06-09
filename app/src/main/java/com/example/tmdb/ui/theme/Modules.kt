package com.example.tmdb.ui.theme

import org.koin.androidx.compose.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Database
import androidx.room.Room
import com.example.tmdb.data.Client
import com.example.tmdb.database.DbMovie
import com.example.tmdb.database.MovieDao
import com.example.tmdb.repository.*
import com.example.tmdb.repository.MovieApiImpl
import com.example.tmdb.repository.MovieRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get

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

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
                com.example.tmdb.database.Database::class.java, "Database"
        ).build()
    }
    single<MovieDao> {
        val database = get<com.example.tmdb.database.Database>()
        database.movieDao()
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