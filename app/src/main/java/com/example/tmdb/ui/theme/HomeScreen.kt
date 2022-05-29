package com.example.tmdb.ui.theme

import android.content.ContentValues
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.R
import com.example.tmdb.repository.MovieResponse
import kotlinx.coroutines.runBlocking
import coil.compose.rememberImagePainter

var selectedMovie = 0
var selectedMovieList = mutableListOf<MovieResponse>()

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel,
    mainViewModel: MainViewModel = MainViewModel()
) {
    val focusRequester = remember { FocusRequester() }
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    val popularCategories = listOf("Streaming", "For TV", "For rent","In theaters")
    val freeToWatchCategories = listOf("Movies", "TV")
    val trendingCategories = listOf("Today", "This week")

    var query = remember {mutableStateOf("")}

    var popularMovies = homeViewModel?.popularMovies?.collectAsState(initial = emptyList())?.value.toMutableList()
    var freeToWatchMovies = homeViewModel?.nowPlayingMovies?.collectAsState(initial = emptyList())?.value.toMutableList()
    var trendingMovies = homeViewModel?.upcomingMovies?.collectAsState(initial = emptyList())?.value.toMutableList()
    var topRatedMovies = homeViewModel?.topRatedMovies?.collectAsState(initial = emptyList())?.value.toMutableList()
    var favoriteMovies = favoritesViewModel.moviesFavorite.collectAsState(initial = emptyList()).value.toMutableList()

    var allMovies : MutableList<MovieResponse> = ArrayList()
    allMovies.addAll(popularMovies)

    for(movie in freeToWatchMovies){
        if(movie !in allMovies){
            allMovies.add(movie)
        }
    }
    for(movie in trendingMovies){
        if(movie !in allMovies){
            allMovies.add(movie)
        }
    }
    for(movie in topRatedMovies){
        if(movie !in allMovies){
            allMovies.add(movie)
        }
    }

    var popular = listOf<String>()
    var freeToWatch = listOf<String>()
    var trending = listOf<String>()

    if (popularMovies != null) {
        for (el in popularMovies){
            popular = popular + el.poster_path
        }
    }
    if (freeToWatchMovies != null) {
        for (el in freeToWatchMovies){
            freeToWatch = freeToWatch + el.poster_path
        }
    }
    if (trendingMovies != null) {
        for (el in trendingMovies){
            trending = trending + el.poster_path
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarMain()
        },
        content = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                MainAppBar(
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = {
                        mainViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                    },
                    onSearchClicked = {
                        Log.d("Searched Text", it)
                    },
                    onSearchTriggered = {
                        mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    }
                )

                SecondaryTitle(
                    text = "Popular",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(popularCategories)
                ScrollableMovieRow(popularMovies, favoriteMovies,favoritesViewModel, Screen.MovieScreen, allMovies)
                SecondaryTitle(
                    text = "Now Playing",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(freeToWatchCategories)
                ScrollableMovieRow(freeToWatchMovies, favoriteMovies, favoritesViewModel, Screen.MovieScreen, allMovies)
                SecondaryTitle(
                    text = "Upcoming",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(trendingCategories)
                ScrollableMovieRow(trendingMovies, favoriteMovies, favoritesViewModel, Screen.MovieScreen, allMovies)
                SecondaryTitle(
                    text = "Top Rated",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                ScrollableMovieRow(topRatedMovies, favoriteMovies, favoritesViewModel, Screen.MovieScreen, allMovies)
                Spacer(Modifier.height(60.dp))
            }
        },
        bottomBar = {
            BottomBarMain("home")
        }
    )
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = "Click to browse movies, TV shows..",
                color = Color.Gray,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        },
        backgroundColor = Color.Transparent,
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFF999999)
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        color = Color(0x77DDDDDD)
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search...",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        if (text.isNotEmpty()) {
//                            onTextChange("")
//                        } else {
//                            onCloseClicked()
//                        }
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Close Icon",
//                        tint = Color.Black
//                    )
//                }
//            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black.copy(alpha = ContentAlpha.medium)
            ))
    }
}

@Composable
fun SubCategories(
    options: List<String>
) {
    var selectedOption by remember {
        mutableStateOf(options.get(0))
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 5.dp),
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(
                        end = 10.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
            ) {
                Text(
                    text = text,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (text != selectedOption) {
                        Color.Black
                    } else {
                        Color(0xFF0222A6)
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 12.dp
                            )
                        )
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text != selectedOption) {
                                Color.White
                            } else {
                                Color(0x77DDDDDD)
                            }
                        )
                        .padding(
                            vertical = 5.dp,
                            horizontal = 10.dp
                        )
                )
            }
        }
    }
}


@Composable
fun TopBarMain(){
    TopAppBar(
        backgroundColor = Color(0xFF0B253F),
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.title),
                    contentDescription = "Title",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(35.dp)
                )
            }
        }
    )
}

@Composable
fun BottomBarMain(
    screen: String
){
    BottomAppBar(backgroundColor = Color(0xFFDDDDDD)) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = {Navigator.navigateTo(Screen.HomeScreen)},
                Modifier.padding(horizontal = 50.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(screen == "home" || screen == "movie") {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home Button"
                        )
                    }
                    if(screen == "favorites") {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home Button"
                        )
                    }
                    Text(text = "Home", fontSize = 10.sp)
                }
            };

            IconButton(onClick = {Navigator.navigateTo(Screen.FavoritesScreen)}, Modifier.padding(horizontal = 50.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(screen == "home" || screen == "movie"){
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favourites Button"
                        )
                    }
                    if(screen == "favorites"){
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favourites Button"
                        )
                    }
                    Text(text = "Favourites", fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun SecondaryTitle(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0B253F)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScrollableMovieRow(
    movieList: List<MovieResponse>,
    favorites: List<MovieResponse>,
    favoritesViewModel: FavoritesViewModel,
    defaultMovieRoute: Screen,
    allMovies: MutableList<MovieResponse>
){
    var favs = listOf<Int>()
    if (favorites != null) {
        for (el in favorites){
            favs = favs + el.id
        }
    }

    ScrollableTabRow(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .border(0.dp, Color.White, RectangleShape),
        selectedTabIndex = 0,
        indicator = { tabPositions ->
            Box(Modifier.height(0.dp)){}
        },
        divider = { Divider(thickness = 0.dp) }
    ) {
        for (item in movieList) {
            if (item.id in favs){
                MovieCardFinal(item.poster_path, true, defaultMovieRoute, favoritesViewModel, allMovies, item.id)
            }
            else{
                MovieCardFinal(item.poster_path, false, defaultMovieRoute, favoritesViewModel, allMovies, item.id)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieCard(
    id: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    favorite: Boolean,
    route: Screen,
    favoritesViewModel: FavoritesViewModel,
    allMovies: MutableList<MovieResponse>,
    movieId: Int
){
    Card(
        modifier = modifier
            .width(109.dp)
            .height(160.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 2.dp
    ) {
        var favoriteMovies = favoritesViewModel.moviesFavorite.collectAsState(initial = emptyList()).value
        var ids = mutableListOf<Int>()
        for(movie in favoriteMovies){
            ids.add(movie.id)
        }

        Box() {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500" + id),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = {
                        selectedMovie = movieId
                        for(mov in allMovies){
                            if(mov.id == movieId){
                                selectedMovieList.add(mov)
                                selectedMovieList.set(0, mov)
                            }
                        }
                        Navigator.navigateTo(Screen.MovieScreen)
                    }))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
                contentAlignment = Alignment.TopStart
            ) {
                var iconState by remember {
                    mutableStateOf(favorite)
                }
                FloatingActionButton(
                    onClick = {
                        iconState = !iconState
                        if (iconState) {
                            runBlocking<Unit> {
                                for(el in allMovies){
                                    if(el.id == movieId){
                                        if(el.id !in ids) {
                                            favoritesViewModel.setFavorite(el)
                                        }
                                    }
                                }
                            }
                        } else {
                            runBlocking<Unit> {
                                for(el in allMovies){
                                    if(el.id == movieId){
                                        if(el.id in ids) {
                                            favoritesViewModel.removeFromFavorites(el)
                                        }
                                    }
                                }
                            }
                        }
                    },
                    contentColor = Color(0xFFFFFFFF),
                    backgroundColor = Color(0x33DDDDDD),
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                ) {
                    Icon(
                        imageVector = if(iconState == true){
                            Icons.Filled.Favorite
                        } else{
                            Icons.Filled.FavoriteBorder
                        },
                        contentDescription = "Favorite Button"
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieCardFinal(
    id: String,
    favorite: Boolean,
    route: Screen,
    favoritesViewModel: FavoritesViewModel,
    allMovies: MutableList<MovieResponse>,
    movieId : Int
){
    Box(
        modifier = Modifier
            .padding(5.dp)
    ) {
        MovieCard(
            id = id,
            contentDescription = "Movie Image",
            favorite = favorite,
            route = route,
            favoritesViewModel = favoritesViewModel,
            allMovies = allMovies,
            movieId = movieId
        )
    }
}

@Composable
fun CategoryButton(text: String, section: List<Int>, active: Int) {
    val selected = remember { mutableStateOf(false) }
    Button(onClick = {selected.value = !selected.value}, colors = ButtonDefaults.textButtonColors(
        backgroundColor = Color.White,
    ),  modifier = Modifier
        .padding(0.dp),
        elevation = ButtonDefaults.elevation(0.dp,0.dp),
        contentPadding = PaddingValues(5.dp)) {
        if(selected.value){
            Text(text,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        textAlign = TextAlign.Start)
        }
        else{
            Text(text,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Start)
        }
    }
}


////@ExperimentalMaterialApi
////@Preview
////@Composable
////fun HomeScreenPreview() {
////    HomeScreen(rememberNavController())
////}
//
//@Preview
//@Composable
//fun SecondaryPreview(){
//    SecondaryTitle(text = "gfggjhgjhgjh", modifier = Modifier)
//}