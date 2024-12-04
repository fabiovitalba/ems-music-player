package com.unibz.songplayer.ui

import androidx.lifecycle.ViewModel
import com.unibz.songplayer.data.Datasource
import com.unibz.songplayer.data.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SongPlayerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SongPlayerUiState())
    val uiState: StateFlow<SongPlayerUiState> = _uiState.asStateFlow()

    // Songs in the current playlist
    lateinit var playlist: MutableList<Song>
    val albums = Datasource().loadAlbums()

    init {
        resetSongPlayer()
    }

    fun resetSongPlayer() {
        _uiState.value = SongPlayerUiState()
        playlist = mutableListOf() // Empty List
    }

    fun selectAlbumById(selectedAlbumId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentAlbumId = selectedAlbumId,
                currentSongId = 0,
                songPaused = currentState.songPaused
            )
        }
    }

    fun selectSongById(selectedSongId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentAlbumId = currentState.currentAlbumId,
                currentSongId = selectedSongId,
                songPaused = currentState.songPaused
            )
        }
    }

    fun playPauseSong() {
        _uiState.update { currentState ->
            currentState.copy(
                songPaused = !currentState.songPaused
            )
        }
    }

    fun nextSong() {
        val songs = albums[_uiState.value.currentAlbumId].songs
        val currSongId = _uiState.value.currentSongId
        var nextSongId = if (currSongId >= songs.size - 1) 0 else currSongId + 1
        _uiState.update { currentState ->
            currentState.copy(
                currentAlbumId = currentState.currentAlbumId,
                currentSongId = nextSongId,
                songPaused = currentState.songPaused
            )
        }
    }

    fun previousSong() {
        val songs = albums[_uiState.value.currentAlbumId].songs
        val currSongId = _uiState.value.currentSongId
        var nextSongId = if (currSongId <= 0) songs.size - 1 else currSongId - 1
        _uiState.update { currentState ->
            currentState.copy(
                currentAlbumId = currentState.currentAlbumId,
                currentSongId = nextSongId,
                songPaused = currentState.songPaused
            )
        }
    }
}