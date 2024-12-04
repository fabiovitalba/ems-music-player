package com.unibz.songplayer.ui

import com.unibz.songplayer.data.Album

data class SongPlayerUiState (
    val currentSongId: Int = 0,
    val currentAlbumId: Int = 0,
    val songPaused: Boolean = true,
    var albums: List<Album>
)