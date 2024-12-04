package com.unibz.songplayer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibz.songplayer.data.Datasource

enum class SongPlayerScreen() {
    AlbumList,
    SongList,
    PlaySong
}

@Composable
fun SongPlayerApp (
    viewModel: SongPlayerViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = SongPlayerScreen.AlbumList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SongPlayerScreen.AlbumList.name) {
                AlbumsListLayout(
                    albumList = uiState.albums,
                    onSelectAlbum = { albumId -> selectAlbum(viewModel, navController, albumId) }
                )
            }
            composable(route = SongPlayerScreen.SongList.name) {
                SongListLayout(
                    songList = uiState.albums[uiState.currentAlbumId].songs,
                    onSelectSong = { songId -> selectSong(viewModel, navController, songId) },
                    onBackButtonClicked = { returnToAlbumSelection(viewModel, navController) }
                )
            }
            composable(route = SongPlayerScreen.PlaySong.name) {
                PlaySongLayout(
                    album = uiState.albums[uiState.currentAlbumId],
                    song = uiState.albums[uiState.currentAlbumId].songs[uiState.currentSongId],
                    isPlaying = !uiState.songPaused,
                    onBackButtonClicked = { returnToSongSelection(navController) },
                    onPlayButtonClicked = { playPauseSong(viewModel) },
                    onNextButtonClicked = { skipToNextSong(viewModel) },
                    onPrevButtonClicked = { skipToPreviousSong(viewModel) },
                    onSongIsFinished = { skipToNextSong(viewModel) }
                )
            }
        }
    }
}

private fun selectAlbum(
    viewModel: SongPlayerViewModel,
    navController: NavHostController,
    albumId: Int
) {
    viewModel.selectAlbumById(albumId)
    navController.navigate(SongPlayerScreen.SongList.name)
}

private fun selectSong(
    viewModel: SongPlayerViewModel,
    navController: NavHostController,
    songId: Int
) {
    viewModel.selectSongById(songId)
    navController.navigate(SongPlayerScreen.PlaySong.name)
}

private fun returnToAlbumSelection(
    viewModel: SongPlayerViewModel,
    navController: NavHostController
) {
    navController.popBackStack(SongPlayerScreen.AlbumList.name, inclusive = false)
    viewModel.resetSongPlayer()
}

private fun returnToSongSelection(
    navController: NavHostController
) {
    navController.popBackStack(SongPlayerScreen.SongList.name, inclusive = false)
}

private fun playPauseSong(viewModel: SongPlayerViewModel) {
    viewModel.playPauseSong()
}

private fun skipToNextSong(viewModel: SongPlayerViewModel) {
    viewModel.nextSong()
}

private fun skipToPreviousSong(viewModel: SongPlayerViewModel) {
    viewModel.previousSong()
}
