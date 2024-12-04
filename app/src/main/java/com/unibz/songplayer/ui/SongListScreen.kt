package com.unibz.songplayer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.R
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
            onClick = onBackButtonClicked,
            modifier = modifier
                .padding(12.dp)
        ) {
            Text(stringResource(R.string.go_back))
        }
        Spacer(modifier.height(2.dp))
        LazyColumn( modifier = modifier ) {
            itemsIndexed(songList) { index, song ->
                SongCardLayout(index, song, onSelectSong, modifier)
            }
        }
        Spacer(modifier.height(2.dp))
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
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 5.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        onClick = { onSelectSong(songId) }
    ) {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = "${songId+1}. ${song.title}",
                modifier = modifier
                    .padding(bottom = 2.dp, start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = song.artist,
                modifier = modifier
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
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
