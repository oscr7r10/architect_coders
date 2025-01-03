package com.oscar.moviesproject.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oscar.moviesproject.data.PosterItemModel

@Composable
fun PosterItem(posterItemModel: PosterItemModel, modifier: Modifier = Modifier, onClick: () ->Unit) {
    Column {
        Box {
            AsyncImage(
                model = posterItemModel.image,
                contentDescription = posterItemModel.title,
                modifier = modifier
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
                    .aspectRatio(ratio = 2 / 3f)
                    .clip(MaterialTheme.shapes.small)
            )
        }
        Text(
            text = posterItemModel.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}