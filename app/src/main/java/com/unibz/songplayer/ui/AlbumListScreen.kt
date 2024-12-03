package com.unibz.songplayer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Album
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.ui.theme.SongPlayerTheme

@Composable
fun AlbumsListLayout(albumList: List<Album>, modifier: Modifier = Modifier) {
    LazyColumn( modifier = modifier ) {
        items(albumList) { album ->
            AlbumCard(album, modifier)
        }
    }
}

@Composable
fun AlbumCard(album: Album, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(album.albumArt),
                contentDescription = "${album.title} - ${album.mainArtist}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "${album.title} - ${album.mainArtist}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumsListLayoutPreview() {
    SongPlayerTheme( darkTheme = true ) {
        AlbumsListLayout(Datasource().loadAlbums())
    }
}