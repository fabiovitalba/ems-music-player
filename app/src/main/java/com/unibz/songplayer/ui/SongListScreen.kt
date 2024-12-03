package com.unibz.songplayer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.data.Song
import com.unibz.songplayer.ui.theme.SongPlayerTheme

@Composable
fun SongListLayout(
    songList: List<Song>,
    onSelectSong: (songIndex: Int) -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        OutlinedButton(
            onClick = onBackButtonClicked
        ) {
            Text("Go Back"/* stringResource(R.string.goBack) */)
        }
        Spacer(modifier.height(12.dp))
        LazyColumn( modifier = modifier ) {
            itemsIndexed(songList) { index, song ->
                SongCardLayout(index, song, onSelectSong, modifier)
            }
        }
    }
}

@Composable
fun SongCardLayout(
    songId: Int,
    song: Song,
    onSelectSong: (songIndex: Int) -> Unit = {},
    modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier
            .padding(16.dp),
        onClick = { onSelectSong(songId) }
    ) {
        Column {
            Text(
                text = "${songId+1}. ${song.title}",
                modifier = modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = song.artist,
                modifier = modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SelectAlbumSongPreview() {
    SongPlayerTheme() {
        SongListLayout(Datasource().loadAlbums()[0].songs)
    }
}
