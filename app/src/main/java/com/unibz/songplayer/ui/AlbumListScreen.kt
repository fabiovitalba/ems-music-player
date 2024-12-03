package com.unibz.songplayer.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.data.Album
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.ui.theme.SongPlayerTheme

@Composable
fun AlbumsListLayout(
    albumList: List<Album>,
    onSelectAlbum: (albumIndex: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn( modifier = modifier ) {
        itemsIndexed(albumList) { index, album ->
            AlbumCard(index, album, onSelectAlbum, modifier)
        }
    }
}

@Composable
fun AlbumCard(
    index: Int,
    album: Album,
    onSelectAlbum: (albumIndex: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState(0)
    OutlinedCard(
        modifier = modifier
            .padding(16.dp),
        onClick = { onSelectAlbum(index) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(album.albumArt),
                contentDescription = "${album.title} - ${album.mainArtist}",
                modifier = modifier
                    .fillMaxWidth()
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    },
                contentScale = ContentScale.Crop
            )
            Text(
                text = "${album.title} - ${album.mainArtist}",
                modifier = modifier
                    .padding(16.dp)
                    .height(28.dp)
                    .horizontalScroll(scrollState),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumsListLayoutPreview() {
    SongPlayerTheme() {
        AlbumsListLayout(Datasource().loadAlbums())
    }
}