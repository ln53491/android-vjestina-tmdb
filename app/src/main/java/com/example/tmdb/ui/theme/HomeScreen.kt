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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.R

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    info: HomeScreenModel
) {
    val focusRequester = remember { FocusRequester() }
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val popularCategories = listOf("Streaming", "For TV", "For rent","In theaters")
    val freeToWatchCategories = listOf("Movies", "TV")
    val trendingCategories = listOf("Today", "This week")

    var text = ""

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarMain()
        },
        content = {
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp), shape = RoundedCornerShape(50.dp),
//                                backgroundColor = Color(0xFFDDDDDD)){
//                Row() {
//                    IconButton(onClick = { /*bruh*/}) {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            modifier = Modifier,
//                            contentDescription = "Search Bar"
//                        )
//                    }
//                    Text(text = "Search...")
//                }
//            };
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp), shape = RoundedCornerShape(50.dp),
//                backgroundColor = Color(0xFFDDDDDD)){
//                Row() {
//                    IconButton(onClick = { /*bruh*/}) {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            modifier = Modifier,
//                            contentDescription = "Search Bar"
//                        )
//                    }
//                    Text(text = "Search...")
//                }
//            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 10.dp), shape = RoundedCornerShape(50.dp),
                                backgroundColor = Color(0xFFDDDDDD)){

                }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp), shape = RoundedCornerShape(50.dp),
                backgroundColor = Color(0xFFEEEEEE)){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    color = Color(0xFFEEEEEE),
                    elevation = 0.dp
                ) {
                    TextField(value = text, onValueChange = {
                        /*onlick*/
                    },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Search...",
                                modifier = Modifier.alpha(ContentAlpha.medium),
                                color = Color.Black)
                        },
                        singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = { /*bruh*/}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                modifier = Modifier.alpha(ContentAlpha.medium),
                                contentDescription = "Search Icon",
                                tint = Color.Black
                        )
                    }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                                if(text.isNotEmpty()){
//                                    onTextChange("")
                                } else{
//                                    onCloseClicked()
                                }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.Gray
                            )
                        }
                    })

                }
            }

                SecondaryTitle(
                    text = "What's popular",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(popularCategories)
                ScrollableMovieRow(navController, info.popularTab,Screen.Movie.route)
                SecondaryTitle(
                    text = "Free to watch",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(freeToWatchCategories)
                ScrollableMovieRow(navController, info.freetowatchTab,Screen.Movie.route)
                SecondaryTitle(
                    text = "Trending",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 0.dp)
                        .align(alignment = Alignment.Start)
                )
                SubCategories(trendingCategories)
                ScrollableMovieRow(navController, info.trendingTab,Screen.Movie.route)
                Spacer(Modifier.height(60.dp))
            }
        },
        bottomBar = {
            BottomBarMain("home", navController)
        }
    )
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
                                Color(0x22020202)
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
                    painter = painterResource(id = com.example.tmdb.ui.theme.titleImage),
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
    HomeScreen(rememberNavController(), defaultHome)
}