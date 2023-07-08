package com.example.tvshows.tvshows.ui.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.tvshows.R
import com.example.tvshows.tvshows.ui.model.ShowUI

@Composable
fun ListShows(
    shows: List<ShowUI>,
    isShorted: Boolean,
    onShortList: () -> Unit
) {
    val lazyListState = rememberLazyListState()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Title()
            }
            items(shows) {
                CardContent(show = it)
            }
        }
        if(!isShorted) {
            FloatingActionButton(
                onClick = {
                    onShortList()
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                content = {
                    Icon(Icons.Filled.List, contentDescription = "Add")
                }
            )
        }
    }
}

@Composable
private fun Title(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.tv_shows),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
private fun CardContent(
    show: ShowUI
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(show.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.show_image),
                loading = {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                },
                modifier = Modifier.size(70.dp).clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    Text(
                        text = show.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardContentPreview() {
    CardContent(ShowUI(1,"Friends", "aUrl"))
}

@Preview(showBackground = true)
@Composable
fun ListShowsPreview() {
    ListShows(listOf<ShowUI>(
        ShowUI(1,"Friends", "aUrl"),
        ShowUI(2,"Superman", "aUrl"),
        ShowUI(3,"The Simpsons", "aUrl3")
    ), false) {}
}
