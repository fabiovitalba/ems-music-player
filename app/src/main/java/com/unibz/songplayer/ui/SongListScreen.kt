package com.unibz.songplayer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.data.Song
import com.unibz.songplayer.ui.theme.SongPlayerTheme

@Composable
fun SongListLayout(songList: List<Song>, modifier: Modifier = Modifier) {
    LazyColumn( modifier = modifier ) {
        itemsIndexed(songList) { index, song ->
            SongCardLayout(index, song, modifier)
        }
    }
}

@Composable
fun SongCardLayout(songId: Int, song: Song, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = "${songId+1}. ${song.title}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = song.artist,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SelectAlbumSongPreview() {
    SongPlayerTheme( darkTheme = true ) {
        SongListLayout(Datasource().loadAlbums()[0].songs)
    }
}
