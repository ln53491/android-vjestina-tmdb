package com.example.tmdb.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.ui.theme.Screen
import com.example.tmdb.data.favoritesMap
import com.example.tmdb.repository.MovieResponse
import com.example.tmdb.ui.theme.*
import java.lang.Integer.max


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    homeViewModel: HomeViewModel
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    var popularMovies = homeViewModel?.popularMovies?.collectAsState(initial = emptyList())?.value
    var freeToWatchMovies = homeViewModel?.nowPlayingMovies?.collectAsState(initial = emptyList())?.value
    var trendingMovies = homeViewModel?.upcomingMovies?.collectAsState(initial = emptyList())?.value

    var allMovies : MutableList<MovieResponse> = ArrayList()
    allMovies.addAll(popularMovies)
    allMovies.addAll(freeToWatchMovies)
    allMovies.addAll(trendingMovies)

    var favoriteMovies = favoritesViewModel?.moviesFavorite?.collectAsState(initial = emptyList())?.value
    var favorites = listOf<Int>()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarMain()
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SecondaryTitle(
                    text = "Favorites",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .align(alignment = Alignment.Start)
                )
                Spacer(Modifier.height(20.dp))

                if(favorites != null){
                    FavouritesRow(favoriteMovies, favoritesViewModel, allMovies)
                }
            }
        },
        bottomBar = {
            BottomBarMain("favorites")
        }
    )
}



@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun FavouritesRow(
    favoriteMovies: List<MovieResponse>,
    favoritesViewModel: FavoritesViewModel,
    allMovies: MutableList<MovieResponse>,
) {
    SimpleFlowRow(
        verticalGap = 15.dp,
        horizontalGap = 0.dp,
        alignment = Alignment.Start,
//        modifier = Modifier.padding(20.dp)
    ) {
        for (tag in favoriteMovies) {
            MovieCardFinal(id = tag.poster_path, favorite = true, Screen.MovieScreen, favoritesViewModel, allMovies, tag.id)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SimpleFlowRow(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: @Composable () -> Unit
) = Layout(content, modifier) { measurables, constraints ->
    val hGapPx = horizontalGap.roundToPx()
    val vGapPx = verticalGap.roundToPx()

    val rows = mutableListOf<MeasuredRow>()
    val itemConstraints = constraints.copy(minWidth = 0)

    for (measurable in measurables) {
        val lastRow = rows.lastOrNull()
        val placeable = measurable.measure(itemConstraints)

        if (lastRow != null && lastRow.width + hGapPx + placeable.width <= constraints.maxWidth) {
            lastRow.items.add(placeable)
            lastRow.width += hGapPx + placeable.width
            lastRow.height = max(lastRow.height, placeable.height)
        } else {
            val nextRow = MeasuredRow(
                items = mutableListOf(placeable),
                width = placeable.width,
                height = placeable.height
            )

            rows.add(nextRow)
        }
    }

    val width = rows.maxOfOrNull { row -> row.width } ?: 0
    val height = rows.sumBy { row -> row.height } + max(vGapPx.times(rows.size - 1), 0)

    val coercedWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
    val coercedHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)

    layout(coercedWidth, coercedHeight) {
        var y = 0

        for (row in rows) {
            var x = when(alignment) {
                Alignment.Start -> 0
                Alignment.CenterHorizontally -> (coercedWidth - row.width) / 2
                Alignment.End -> coercedWidth - row.width

                else -> throw Exception("unsupported alignment")
            }

            for (item in row.items) {
                item.place(x, y)
                x += item.width + hGapPx
            }

            y += row.height + vGapPx
        }
    }
}

private data class MeasuredRow(
    val items: MutableList<Placeable>,
    var width: Int,
    var height: Int
)