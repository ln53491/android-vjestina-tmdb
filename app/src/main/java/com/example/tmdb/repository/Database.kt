package com.example.tmdb.repository

class Database {

    var favorites : MutableList<MovieResponse> = mutableListOf()

    fun removeFavorite(movie: MovieResponse) : List<MovieResponse> {
        favorites.remove(movie)
        var index = 1
        for (item in favorites) {
            item.id = index
            index += 1
        }
        return favorites
    }

    fun setFavorite(movie: MovieResponse) : List<MovieResponse> {
        favorites.add(movie)
        return favorites
    }

}