package com.unibz.songplayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.ui.theme.SongPlayerTheme

val gradientBrush =
    Brush.verticalGradient(
        colors = listOf(Color.Red, Color.Blue, Color.Green),
        startY = 0.0f,
        endY = 3000.0f
    )

@Composable
fun PlaySongLayout(modifier: Modifier = Modifier) {
    // App State
    var songId by rememberSaveable { mutableIntStateOf(0) }
    var albumId by rememberSaveable { mutableIntStateOf(0) }
    var songProgress by rememberSaveable { mutableFloatStateOf(0.0f) }

    val albums = Datasource().loadAlbums()
    val album = albums[albumId]
    val song = album.songs[songId]

    Box(modifier = Modifier
        /*.background(brush = gradientBrush)*/
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                OutlinedButton(onClick = { /* TODO Go to Album Selection */ }) {
                    Text("Go Back"/* stringResource(R.string.next) */)
                }
            }
            Button(onClick = { /* TODO Go to Album Song List */ }) {
                Image(
                    painter = painterResource(album.albumArt),
                    contentDescription = album.title,
                    modifier = Modifier
                        .height(250.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 8.dp,
                                topStart = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp
                            )
                        ),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { songProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = song.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = song.artist,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = album.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium
            )
            Row {
                FilledTonalButton(onClick = { /* TODO Previous song */ }) {
                    Text("PREV"/* stringResource(R.string.next) */)
                }
                Button(onClick = { /* TODO Start/Stop Song */ }) {
                    Text("PLAY/PAUSE"/* stringResource(R.string.next) */)
                }
                FilledTonalButton(onClick = { /* TODO Next song */ }) {
                    Text("NEXT"/* stringResource(R.string.next) */)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaySongPreview() {
    SongPlayerTheme( darkTheme = true ) {
        PlaySongLayout()
    }
}
