package com.example.tmdb.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Movie
import com.example.tmdb.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel(){

    fun getPopularMovies(): List<Movie>? {
        return repository.getPopularMovies()
    }

    fun getFreeToWatchMovies(): List<Movie>? {
        return repository.getFreeToWatchMovies()
    }

    fun getTrendingMovies(): List<Movie>? {
        return repository.getTrendingMovies()
    }

    fun getFavoriteMovies(): List<Movie>? {
        return repository.getFavoriteMovies()
    }

    fun getFavoriteById(id: Int): Movie? {
        return repository.getFavoriteById(id = id)
    }

    fun setFavorite(id: Int) {
        return repository.setFavorite(id = id)
    }

    fun removeFavorite(id: Int) {
        return repository.removeFavorite(id = id)
    }
}


class FavoritesViewModel(private val repository: MovieRepository) : ViewModel(){

    fun getFavoriteMovies(): List<Movie>? {
        return repository.getFavoriteMovies()
    }

    fun getFavoriteById(id: Int): Movie? {
        return repository.getFavoriteById(id = id)
    }

    fun setFavorite(id: Int) {
        return repository.setFavorite(id = id)
    }

    fun removeFavorite(id: Int) {
        return repository.removeFavorite(id = id)
    }
}


class MovieViewModel(private val repository: MovieRepository) : ViewModel(){

    fun getFavoriteMovies(): List<Movie>? {
        return repository.getFavoriteMovies()
    }

    fun getFavoriteById(id: Int): Movie? {
        return repository.getFavoriteById(id = id)
    }

    fun setFavorite(id: Int) {
        return repository.setFavorite(id = id)
    }

    fun removeFavorite(id: Int) {
        return repository.removeFavorite(id = id)
    }
}