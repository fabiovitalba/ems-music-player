package com.unibz.songplayer.ui

data class SongPlayerUiState (
    val currentSongId: Int = 0,
    val currentAlbumId: Int = 0,
    val songPaused: Boolean = true
)