package com.unibz.songplayer.data

import androidx.annotation.DrawableRes

data class Album(val title: String, val mainArtist: String, val songs: List<Song>, @DrawableRes val albumArt: Int) {

}