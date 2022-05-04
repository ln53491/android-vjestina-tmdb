//package com.example.tmdb.ui.theme
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.tmdb.repository.Movie
//import com.example.tmdb.repository.MovieRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.launch
//
//class HomeViewModel(val movieRepository : MovieRepository) : ViewModel() {
//
//    var popularMovies : Flow<List<Movie>> = MutableStateFlow(emptyList())
//    var freeToWatchMovies : Flow<List<Movie>> = MutableStateFlow(emptyList())
//    var trendingMovies : Flow<List<Movie>> = MutableStateFlow(emptyList())
//
//    suspend fun addFavoriteMovie(movie : Movie) = movieRepository.setFavorite(movie)
//    suspend fun removeFromFavorites(movie : Movie) = movieRepository.removeFavorite(movie)
//
//    init {
//        viewModelScope.launch {
//            popularMovies = movieRepository.getPopularMovies()
//            freeToWatchMovies = movieRepository.getFreeToWatchMovies()
//            trendingMovies = movieRepository.getTrendingMovies()
//        }
//    }
//}
//
//
//class FavoritesViewModel(val movieRepository : MovieRepository) : ViewModel() {
//
//    var moviesFavorite : Flow<List<Movie>> = MutableStateFlow(emptyList())
//    suspend fun setFavorite(movie : Movie) = movieRepository.setFavorite(movie)
//    suspend fun removeFromFavorites(movie : Movie) = movieRepository.removeFavorite(movie)
//
//    init {
//        viewModelScope.launch { moviesFavorite = movieRepository.getFavoriteMovies() }
//    }
//}