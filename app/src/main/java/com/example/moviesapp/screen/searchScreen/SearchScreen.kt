package com.example.movieapp.screen.searchScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesapp.R
import com.example.moviesapp.data.CategoryRepository
import com.example.moviesapp.data.MovieRepository
import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val categoryRepository = CategoryRepository()
    val movieRepository = MovieRepository()
    var categories by remember { mutableStateOf(emptyList<CategoryMovie>()) }
    var categoriesLimit by remember { mutableStateOf(emptyList<CategoryMovie>()) }
    var movies by remember { mutableStateOf(emptyList<Movie>()) }

    var boolean by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        categories = categoryRepository.getAllCategories()
        categoriesLimit = categoryRepository.getAllCategoriesLimited(6)
        movies = movieRepository.getMoviesLimit(32)
    }
    Scaffold(
        topBar = {
            SearchBarView()
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            items(if (boolean) categoriesLimit else categories) { it ->
                ItemCategoryView(it.name, it.image) {
                    navController.navigate("CategoryMovies/" + it.name+"/"+it.id)
                }
            }

            item(span = { GridItemSpan(2) }) {
                ButtonExpandCollapse(boolean = boolean) {
                    boolean = !boolean
                }
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Có thể bạn quan tâm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, textAlign = TextAlign.Start
                )
            }
            items(movies) { it ->

                ItemMovieView(it.image) {

                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableListOf("123123123", "12312sdfgsdfsd")
    }

    Column(modifier = Modifier.padding(10.dp)) {
        AnimatedVisibility(visible = !active) {
            Text(
                text = "Tìm kiếm",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                items.add(text)
                active = false
                text = ""
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Search", color = Color.White) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "", tint = Color.White
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            colors = SearchBarDefaults.colors(
                Color.Black, Color.White, SearchBarDefaults.inputFieldColors(Color.White)
            ),
            tonalElevation = 100.dp,
        ) {
            Column() {
                Text(
                    text = "Lịch Sử tìm kiếm", style = TextStyle(
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
                    )
                )
                items.forEach {
                    Row(modifier = Modifier.padding(all = 14.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.baseline_history_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = it, color = Color.White)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCategoryView(title: String, image: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(90.dp)
            .width(120.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = onClick

    ) {
        Box() {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemMovieView(image: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(90.dp)
            .width(120.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = onClick

    ) {
        Box() {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun ButtonExpandCollapse(boolean: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            Color.Transparent
        )
    ) {
        Icon(
            if (boolean) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            "",
            tint = Color.White,
        )
        Text(text = if (boolean) "Xem thêm" else "Thu gọn", fontSize = 15.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PriviewSearchScreen() {
}
