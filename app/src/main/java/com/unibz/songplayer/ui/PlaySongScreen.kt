package com.unibz.songplayer.ui

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Album
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.data.Song
import com.unibz.songplayer.ui.theme.SongPlayerTheme
import kotlinx.coroutines.delay

@Composable
fun PlaySongLayout(
    album: Album,
    song: Song,
    isPlaying: Boolean,
    onNextButtonClicked: () -> Unit = {},
    onPrevButtonClicked: () -> Unit = {},
    onPlayButtonClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
    onSongIsFinished: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // App State
    var songProgress by rememberSaveable { mutableFloatStateOf(0.00f) }
    val songProgressDelta = (1f / song.duration)
    Log.i("PlaySongScreen","song.duration ${song.duration}, songProgressDelta $songProgressDelta")

    // LaunchEffect is triggered when isPlaying or songProgress changes.
    LaunchedEffect(
        key1 = isPlaying,
        key2 = songProgress, block = {
            delay(1_000)
            Log.i("PlaySongScreen","refresh done, playing? $isPlaying")
            if (isPlaying) {
                if (songProgress < 1f) {
                    songProgress += songProgressDelta
                } else {
                    songProgress = 0.00f
                    onSongIsFinished()
                }
            }
            Log.i("PlaySongScreen","progress $songProgress")
        }
    )

    Box(modifier = modifier
        /*.background(brush = gradientBrush)*/
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                OutlinedButton(onClick = onBackButtonClicked) {
                    Text("Go Back"/* stringResource(R.string.next) */)
                }
            }
            Image(
                painter = painterResource(album.albumArt),
                contentDescription = album.title,
                modifier = modifier
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
            Spacer(modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { songProgress },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
            )
            Spacer(modifier.height(12.dp))
            Text(
                text = song.title,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = song.artist,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = album.title,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Row {
                FilledTonalButton(onClick = {
                    songProgress = 0.00f
                    onPrevButtonClicked()
                } ) {
                    Text("PREV"/* stringResource(R.string.next) */)
                }
                Button(onClick = onPlayButtonClicked ) {
                    Text(if (isPlaying) "PAUSE" else "PLAY"/* stringResource(R.string.next) */)
                }
                FilledTonalButton(onClick = {
                    songProgress = 0.00f
                    onNextButtonClicked()
                } ) {
                    Text("NEXT"/* stringResource(R.string.next) */)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaySongPreview() {
    val album = Datasource().loadAlbums()[0]
    val song = album.songs[0]
    SongPlayerTheme() {
        PlaySongLayout(
            album = album,
            song = song,
            isPlaying = false
        )
    }
}
