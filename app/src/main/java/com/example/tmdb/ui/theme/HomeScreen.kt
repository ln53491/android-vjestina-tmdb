package com.example.tmdb.ui.theme

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.tmdb.R
import com.example.tmdb.data.Screen
import com.example.tmdb.data.defaultHome
import com.example.tmdb.data.favoritesMap
import com.example.tmdb.viewmodels.MainViewModel

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
//    homeViewModel: HomeViewModel? = null,
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

    var popular = listOf<Int>()
    var freeToWatch = listOf<Int>()
    var trending = listOf<Int>()

//    for (el in homeViewModel?.getPopularMovies()!!){
//        popular = popular + el.image
//    }
//    for (el in homeViewModel?.getFreeToWatchMovies()!!){
//        freeToWatch = freeToWatch + el.image
//    }
//    for (el in homeViewModel?.getTrendingMovies()!!){
//        trending = trending + el.image
//    }

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
                    text = "What's popular",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(popularCategories)
                ScrollableMovieRow(navController, defaultHome.popularTab, Screen.Movie.route)
//                ScrollableMovieRow(navController, popular, Screen.Movie.route)
                SecondaryTitle(
                    text = "Free to watch",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(freeToWatchCategories)
                ScrollableMovieRow(navController, defaultHome.freetowatchTab, Screen.Movie.route)
//                ScrollableMovieRow(navController, freeToWatch, Screen.Movie.route)
                SecondaryTitle(
                    text = "Trending",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(trendingCategories)
                ScrollableMovieRow(navController, defaultHome.trendingTab, Screen.Movie.route)
//                ScrollableMovieRow(navController, trending, Screen.Movie.route)
                Spacer(Modifier.height(60.dp))
            }
        },
        bottomBar = {
            BottomBarMain("home", navController)
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
                        ),
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
    screen: String,
    navController: NavController
){
    BottomAppBar(backgroundColor = Color(0xFFDDDDDD)) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = {navController.navigate(Screen.Home.route)}, Modifier.padding(horizontal = 50.dp)) {
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

            IconButton(onClick = {navController.navigate(Screen.Favorites.route)}, Modifier.padding(horizontal = 50.dp)) {
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
    navController: NavController,
    movieList: List<Int>,
    defaultMovieRoute: String
){
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
            MovieCardFinal(item, favoritesMap.getValue(item),defaultMovieRoute, navController)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieCard(
    id: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    favorite: Boolean,
    route: String,
    navController: NavController
){
    Card(
        modifier = modifier
            .width(109.dp)
            .height(160.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 2.dp
    ) {
        Box() {
            Image(
                painter = painterResource(id = id),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = {
                        if (route.isNotBlank()) {
                            navController.navigate(route)
                        }
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
                        favoritesMap.put(id, !favoritesMap.get(id)!!)
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
    id: Int,
    favorite: Boolean,
    route: String = "",
    navController: NavController = rememberNavController()
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
            navController = navController
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


@ExperimentalMaterialApi
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}