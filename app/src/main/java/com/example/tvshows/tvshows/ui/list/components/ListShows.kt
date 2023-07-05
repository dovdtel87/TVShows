package com.example.tvshows.tvshows.ui.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvshows.R
import com.example.tvshows.data.model.Show

@Composable
fun ListShows(
    shows: List<Show>,
    onShortList: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        Modifier.fillMaxSize(),
        state = lazyListState,

        ) {
        item {
            Title()
        }
        items(shows) {
            CardContent(show = it)
        }
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Short",
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 30.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
                )
            }
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
    show: Show
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                Text(
                    text = show.name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardContentPreview() {
    CardContent(Show("1","Friends", "aUrl"))
}

@Preview(showBackground = true)
@Composable
fun ListShowsPreview() {
    ListShows(listOf<Show>(
        Show("1","Friends", "aUrl"),
        Show("2","Superman", "aUrl"),
        Show("3","The Simpsons", "aUrl3")
    )) {}
}
