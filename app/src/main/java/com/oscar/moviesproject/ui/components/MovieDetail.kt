package com.oscar.moviesproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.oscar.moviesproject.data.Movie

@Composable
fun MovieDetail(
    padding: PaddingValues,
    movie: Movie
) {
    Column (
        modifier = Modifier
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ){
        AsyncImage(
            model = movie.backdrop,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = movie.overview,
            modifier = Modifier.padding(16.dp)
        )
        Text(text = buildAnnotatedString {
            Property(name = "Original Language", value = movie.originalLanguage, end = false)
            Property(name = "Original Title", value = movie.originalTitle, end = false)
            Property(name = "Release Date", value = movie.releaseDate, end = false)
            Property(name = "Popularity", value = movie.popularity.toString(), end = false)
            Property(name = "VoteAverage", value = movie.voteAverage.toString(), end = false)
        }, modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
            )
    }
}
//Permite ir cambiando los estilos de los textos.
@Composable
private fun AnnotatedString.Builder.Property(name: String, value: String, end: Boolean){
    withStyle(ParagraphStyle(lineHeight = 18.sp)){
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
            append("$name: ")
        }
        append(value)
        if (!end){
            append("\n")
        }
    }
}