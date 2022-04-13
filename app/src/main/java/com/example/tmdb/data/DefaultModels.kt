package com.example.tmdb.data

import com.example.tmdb.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

var titleImageMain = R.drawable.title

var favoritesMap = mutableMapOf(
    R.drawable.ironman1 to true,
    R.drawable.gattaca to false,
    R.drawable.lionking to true,
    R.drawable.puppylove to true,
    R.drawable.junglebeat to false,
    R.drawable.civilwar to false,
    R.drawable.ironman2 to true,
    R.drawable.godzilla to true
)

var defaultHome = HomeScreenModel(
    popularTab = listOf(
        R.drawable.ironman1,
        R.drawable.gattaca,
        R.drawable.lionking
    ),
    freetowatchTab = listOf(
        R.drawable.puppylove,
        R.drawable.junglebeat,
        R.drawable.civilwar
    ),
    trendingTab = listOf(
        R.drawable.ironman2,
        R.drawable.godzilla,
        R.drawable.gattaca
    )
)


data class HomeScreenModel(
    val popularTab: List<Int>,
    val freetowatchTab: List<Int>,
    val trendingTab: List<Int>
)