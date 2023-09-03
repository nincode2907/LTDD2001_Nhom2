package com.example.movieapp.screen.searchScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.CategoryMovieBookNavigation
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.MovieBookNavigation
import com.example.moviesapp.screen.homeScreen.component.NotConnected
import com.example.moviesapp.screen.mainScreen.Main.checkNetworkConnectivity
import com.example.moviesapp.screen.searchScreen.SearchSreenViewModel
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    movies: List<Movie>,
) {
    var boolean by remember {
        mutableStateOf(true)
    }

    val searchSreenViewModel: SearchSreenViewModel = hiltViewModel()
    val categoriesState = searchSreenViewModel.categories.collectAsState()

    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(checkNetworkConnectivity(context)) }

    Scaffold(
        topBar = {
            if (isConnected)
                SearchBarView(movies, navController)
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = true
            )
        },
    ) { paddingValues ->
        if (isConnected) {
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
                items(if (boolean) categoriesState.value.sortedBy { it.name }
                    .take(6) else categoriesState.value.sortedBy { it.name }) { it ->
                    ItemCategoryView(it) {
                        navController.navigate(CategoryMovieBookNavigation.createRoute(it))
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
                items(movies.sortedByDescending { it.view ?: 0 }.take(36)) { it ->

                    ItemMovieView(it) {
                        navController.navigate(MovieBookNavigation.createRoute(it))

                    }
                }
            }
        } else {
            NotConnected {
                isConnected = checkNetworkConnectivity(context)
                if (isConnected)
                    Toast.makeText(context, "Đã khôi phục kết nối", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(movies: List<Movie>, navController: NavController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf<String>()
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
                // navController.navigate("movieQuery/" + text)
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
             if (text.isEmpty()) {
                Text(text = "")
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    items(movies.filter { movie ->
                        movie.name.toString().contains(text, true)
                    }) { it ->
                        ItemMovieView(it) {
                            navController.navigate(MovieBookNavigation.createRoute(movie = it))
                        }
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCategoryView(categoryMovie: CategoryMovie, onClick: () -> Unit) {
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
                model = categoryMovie.image,
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
                    text = categoryMovie.name,
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
fun ItemMovieView(movie: Movie, onClick: () -> Unit) {
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
                model = movie.image,
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
