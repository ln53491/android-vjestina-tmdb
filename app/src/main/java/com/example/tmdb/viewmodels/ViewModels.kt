package com.example.tmdb.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieResponse
import com.example.tmdb.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(val movieRepository : MovieRepository) : ViewModel() {

    var popularMovies : Flow<List<MovieResponse>> = MutableStateFlow(emptyList())
    var nowPlayingMovies : Flow<List<MovieResponse>> = MutableStateFlow(emptyList())
    var upcomingMovies : Flow<List<MovieResponse>> = MutableStateFlow(emptyList())
    var topRatedMovies : Flow<List<MovieResponse>> = MutableStateFlow(emptyList())

    suspend fun addFavoriteMovie(movie : MovieResponse) = movieRepository.setFavorite(movie)
    suspend fun removeFromFavorites(movie : MovieResponse) = movieRepository.removeFavorite(movie)

    init {
        viewModelScope.launch {
            popularMovies = movieRepository.getPopularMovies()
            nowPlayingMovies = movieRepository.getNowPlayingMovies()
            upcomingMovies = movieRepository.getUpcomingMovies()
            topRatedMovies = movieRepository.getTopRatedMovies()
        }
    }
}


class FavoritesViewModel(val movieRepository : MovieRepository) : ViewModel() {

    var moviesFavorite : Flow<List<MovieResponse>> = MutableStateFlow(emptyList())
    suspend fun setFavorite(movie : MovieResponse) = movieRepository.setFavorite(movie)
    suspend fun removeFromFavorites(movie : MovieResponse) = movieRepository.removeFavorite(movie)

    init {
        viewModelScope.launch { moviesFavorite = movieRepository.getFavoriteMovies() }
    }
}