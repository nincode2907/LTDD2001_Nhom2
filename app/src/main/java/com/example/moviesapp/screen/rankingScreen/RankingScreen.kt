package com.example.movieapp.screen.rankingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.screen.mainScreen.MainViewModel
import com.example.petadoption.bottomnav.BottomBar
import com.example.rank.Banner
import com.example.rank.BarkHomeContent
import com.example.rank.RankingListItem
import com.example.rank.data.DataProvider
import com.example.rank.data.Ranking

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RankingScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    bottomBarState: MutableState<Boolean>,
) {
    val ranks = remember {
        DataProvider.rankingList
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    Text(
                        text = "Rankking",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },

                )
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        )
        {
            stickyHeader {
                Card(
                    modifier = Modifier
                        .fillMaxHeight(),
                    colors = CardDefaults.cardColors(Color.Black)
                ) {
                    Text(
                        text = "Nổi bật",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                    Banner()
                    Text(
                        text = "Top phim",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                }
            }
            items(ranks) { it ->
                RankingListItem(it) {
                     navController.navigate("test")
                }

            }
        }
    }
}

@Composable
fun test() {
    Text(text = "title", color = Color.Black)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Rank(navigateToProfile: (Ranking) -> Unit) {

    BarkHomeContent(navigateToProfile = navigateToProfile)


}

