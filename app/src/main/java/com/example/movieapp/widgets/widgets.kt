package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (movie: String) -> Unit = {}) {

    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(movie.images[0])
        .crossfade(true)
        .transformations(CircleCropTransformation())
        .build()

    val painter = rememberAsyncImagePainter(imageRequest)
    var expended by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation  = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                color = Color.White
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Movie poster",
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Director: ${ movie.title }", style = MaterialTheme.typography.labelSmall)
                Text(text = "Realeased: ${ movie.year }", style = MaterialTheme.typography.labelSmall)

                AnimatedVisibility(visible = expended) {
                    Column () {
                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        HorizontalDivider(modifier = Modifier.padding(3.dp))

                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                Icon(
                    imageVector = if (expended) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier.size(25.dp)
                        .clickable {
                            expended = !expended
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }
}
