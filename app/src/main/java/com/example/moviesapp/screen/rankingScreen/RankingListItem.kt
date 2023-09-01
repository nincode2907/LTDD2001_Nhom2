package com.example.rank

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.presentation.favourite.FavouriteMoviesModel
import kotlinx.coroutines.launch


@Composable
fun RankingListItem(
    movie: Movie,
    isFavouriteInit: Boolean,
    onClick: () -> Unit,
    viewModel: FavouriteMoviesModel,
    imgTopUrl: String?
) {
    var isFavourite by remember { mutableStateOf(isFavouriteInit) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {  },
        colors = CardDefaults.cardColors(colorResource(id = R.color.dark)),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))

    ) {
        Row(Modifier.clickable(onClick = onClick)) {
            Box() {
                RankingImage(movie)
                if (imgTopUrl != null) {
                    topRank(imgTopUrl)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Row {
                    Text(text = movie.name!!, style = typography.bodyLarge,
                        color = Color.White,
                        maxLines =1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .size(width = 175.dp, height = 30.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.updateFavourite(movie)
                        }
                        isFavourite = !isFavourite
                    },
                        modifier = Modifier.size(30.dp)) {
                        Icon(painter = if(!isFavourite) painterResource(id = R.drawable.outline_bookmark_add_24) else painterResource(id = R.drawable.outline_bookmark_added_24),
                            contentDescription = null, modifier = Modifier
                                .padding(0.dp), tint = Color.White,
                        )
                    }
                }
                Text(text = movie.country!!,  style = TextStyle(color = Color.Gray), fontSize = 10.sp, fontStyle = FontStyle.Italic, maxLines = 1)
                Text(text = movie.description!!, style = typography.bodySmall,
                    color = Color.White,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun RankingImage(movie: Movie) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.image)
            .crossfade(true)
            .build(),
        contentDescription = movie.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(width = 90.dp, height = 120.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(corner = CornerSize(5.dp)))
    )
}

@Composable
private fun topRank(imgTopUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgTopUrl)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}