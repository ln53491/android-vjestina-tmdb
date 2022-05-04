//package com.example.tmdb.screens
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.GridCells
//import androidx.compose.foundation.lazy.LazyVerticalGrid
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.Star
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.layout.Layout
//import androidx.compose.ui.layout.Placeable
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.tmdb.R
//import com.example.tmdb.ui.theme.Screen
//import com.example.tmdb.data.favoritesMap
//import com.example.tmdb.repository.MovieScreenModel
//import com.example.tmdb.repository.defaultMovie
//import com.example.tmdb.screens.SimpleFlowRow
//import com.example.tmdb.ui.theme.TopBarMain
//import java.lang.Integer.max
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@RequiresApi(Build.VERSION_CODES.N)
//@Composable
//fun MovieScreen(
//    navController: NavController,
//    info: MovieScreenModel
//) {
//    val focusRequester = remember { FocusRequester() }
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    var tabIndex = 0
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopBarMain()
//        },
//        content = {
//                Column(modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//                    .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally) {
//                        MovieInfoBar(
//                            score = info.userScore,
//                            title = info.title,
//                            year = info.releaseYear,
//                            release = info.releaseDate,
//                            genres = info.genres,
//                            duration = info.duration,
//                            painter = painterResource(id = info.background),
//                            star = info.star
//                        )
//                    Text("Overview",
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 10.dp)
//                            .align(alignment = Alignment.Start),
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0B253F)
//                    )
//
//                    Text(text = info.overview,
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 0.dp)
//                            .align(alignment = Alignment.Start),
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Normal,
//                        color = Color.Black
//                    );
//                    Spacer(modifier = Modifier.height(20.dp))
//                    InfoRow(info.infoPeople)
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Row(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 0.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically){
//                        Text("Top Billed Cast",
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFF0B253F)
//                        )
//                        Text("Full Cast & Crew",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color.Black
//                        )
//                    }
//
//                    ScrollableTabRow(
//                        backgroundColor = Color.White,
//                        modifier = Modifier
//                            .fillMaxWidth().padding(horizontal = 10.dp)
//                            .border(0.dp, Color.White, RectangleShape),
//                        selectedTabIndex = 0,
//                        indicator = { tabPositions ->
//                            Box(
//                                Modifier
//                                    .height(0.dp)
//                            ){}
//                        },
//                        divider = { Divider(thickness = 0.dp) }
//                    ) {
//                        for(item in info.cast){
//                            CastCardFinal(item.first, item.second, item.third)
//                        }
//                    }
//
//                    Text("Social",
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 10.dp)
//                            .align(alignment = Alignment.Start),
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0B253F)
//                    )
//                    Row(horizontalArrangement = Arrangement.Start,
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 0.dp)
//                            .align(Alignment.Start)) {
//                        Text("Reviews (${info.reviewNumber})",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Normal,
//                            color = Color.Blue,
//                            modifier = Modifier.padding(end = 20.dp)
//                        )
//                        Text("Discussions (${info.discussionNumber})",
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Normal,
//                            color = Color.Black,
//                            modifier = Modifier.padding(end = 20.dp)
//                        )
//                    };
//                    for (item in info.reviews){
//                        ReviewPost(
//                            painter = painterResource(id = item.image),
//                            author = item.author,
//                            date = item.date,
//                            text = item.text)
//                    }
//                    Text("Recommendations",
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 10.dp)
//                            .align(alignment = Alignment.Start),
//                        fontSize = 30.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0B253F)
//                    )
//
//                    ScrollableTabRow(
//                        backgroundColor = Color.White,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .border(0.dp, Color.White, RectangleShape),
//                        selectedTabIndex = 0,
//                        indicator = { tabPositions ->
//                            Box(
//                                Modifier
//                                    .height(0.dp)
//                            ){}
//                        },
//                        divider = { Divider(thickness = 0.dp) }
//                    ) {
//                        for(item in info.recommendations){
//                            MovieCardFinal(item, favoritesMap.getValue(item), Screen.Movie.route, navController)
//                        }
//                    }
//                    Spacer(Modifier.height(80.dp))
//
//                }
//
//        },
//        bottomBar = {
//            BottomBarMain("movie", navController)
//        }
//    )
//}
//
//@ExperimentalMaterialApi
//@Composable
//fun CastCard(
//    painter: Painter,
//    contentDescription: String,
//    modifier: Modifier = Modifier,
//    name: String,
//    character: String
//){
//    Card(
//        modifier = modifier
//            .width(120.dp)
//            .height(190.dp),
//        shape = RoundedCornerShape(15.dp),
//        elevation = 2.dp
//    ) {
//        Box() {
//            Image(
//                painter = painter,
//                contentDescription = contentDescription,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 5.dp))
//        }
//        Column(modifier = Modifier
//            .padding(10.dp),
//            verticalArrangement = Arrangement.Bottom
//        ) {
//            Text(text = "$name", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
//            Text(text = "$character", fontSize = 10.sp, color = Color.Gray)
//        }
//    }
//}
//
////R.drawable.ironman se predaje
//@ExperimentalMaterialApi
//@Composable
//fun CastCardFinal(id: Int, name: String, character: String){
//    Box(
//        modifier = Modifier
//            .padding(5.dp)
//    ) {
//        CastCard(
//            painter = painterResource(id = id),
//            contentDescription = "Movie Image",
//            name = name,
//            character = character
//        )
//    }
//}
//
//@Composable
//fun ReviewPost(
//    painter: Painter,
//    author: String,
//    date: String,
//    text: String
//){
//    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
//        .fillMaxWidth()
//        .padding(20.dp),
//            verticalAlignment = Alignment.CenterVertically){
//        Image(
//            painter = painter,
//            contentDescription = "Author Image",
//            contentScale = ContentScale.Crop,            // crop the image
//            modifier = Modifier
//                .size(64.dp)
//                .clip(CircleShape))
//
//        Column(){
//            Text("A review by $author",
//                modifier = Modifier
//                    .padding(horizontal = 0.dp, vertical = 10.dp)
//                    .align(alignment = Alignment.Start),
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color.Black
//            )
//
//            Text(buildAnnotatedString {
//                withStyle(
//                    style = SpanStyle(
//                        color = Color.Gray,
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                ) {
//                    append("Written by")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = Color.Black,
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                ) {
//                    append(" ($author) ")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = Color.Gray,
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                ) {
//                    append("on")
//                }
//            })
//
//            Text("$date",
//                modifier = Modifier
//                    .padding(horizontal = 0.dp)
//                    .align(alignment = Alignment.Start),
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//                color = Color.Gray
//            )
//        }
//    }
//
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .padding(horizontal = 20.dp, vertical = 0.dp)){
//        Text("$text",
//            fontSize = 15.sp,
//            fontWeight = FontWeight.Normal,
//            color = Color.Gray,
//            modifier = Modifier.padding(bottom = 20.dp))
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@RequiresApi(Build.VERSION_CODES.N)
//@Composable
//fun InfoRow(tags: List<Pair<String, String>>) {
//    SimpleFlowRow(
//        verticalGap = 10.dp,
//        horizontalGap = 0.dp,
//        alignment = Alignment.Start,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        for (item in tags) {
//            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)) {
//                Text("${item.first}",
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black)
//                Text("${item.second}",
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Normal,
//                    color = Color.Black)
//            }
//        }
//    }
//}
//
//@Composable
//fun MovieInfoBar(
//    score: Int,
//    title: String,
//    year: Int,
//    release: String,
//    genres: String,
//    duration: String,
//    painter: Painter,
//    star: Int
//){
//    Box(modifier = Modifier.fillMaxWidth(),
//        contentAlignment = Alignment.BottomEnd) {
//        Image(
//            painter = painter,
//            contentDescription = "Movie Background",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize())
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf(Color.Transparent, Color.Black),
////                        startY = 300f
//                )
//            )){}
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        ) {
//            Column(verticalArrangement = Arrangement.Bottom) {
//
//                Text("$score% - User Score", color = Color.White, fontSize = 20.sp)
//                Spacer(modifier = Modifier.height(10.dp))
//                Text(buildAnnotatedString {
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.White,
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    ) {
//                        append("$title ")
//                    }
//
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.White,
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Normal
//                        )
//                    ) {
//                        append("($year)")
//                    }
//                })
//                Spacer(modifier = Modifier.height(10.dp))
//                Text("$release", color = Color.White, fontSize = 15.sp)
//                Text(buildAnnotatedString {
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.White,
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Normal
//                        )
//                    ) {
//                        append("$genres  ")
//                    }
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.White,
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    ) {
//                        append("$duration")
//                    }
//                }
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//                FloatingActionButton(
//                    onClick = { /**/ },
//                    contentColor = Color(0xFFFFFFFF),
//                    backgroundColor = Color(0x33DDDDDD),
//                    modifier = Modifier
//                        .height(40.dp)
//                        .width(40.dp)
//                ) {
//                    if (star == 0) {
//                        Icon(
//                            modifier = Modifier.padding(5.dp),
//                            imageVector = Icons.Outlined.Star,
//                            contentDescription = "Star Button"
//                        )
//                    } else {
//                        Icon(
//                            modifier = Modifier.padding(5.dp),
//                            imageVector = Icons.Filled.Star,
//                            contentDescription = "Star Button"
//                        )
//                    }
//                }
//
//            }
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.N)
//@Preview
//@Composable
//fun MovieScreenPreview() {
//    MovieScreen(rememberNavController(), defaultMovie)
//}