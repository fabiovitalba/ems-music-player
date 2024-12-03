package com.unibz.songplayer

import androidx.annotation.DrawableRes

data class Album(val title: String, val mainArtist: String, val songs: List<Song>, @DrawableRes val albumArt: Int) {

}