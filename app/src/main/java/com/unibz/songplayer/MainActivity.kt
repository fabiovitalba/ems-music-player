package com.unibz.songplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.unibz.songplayer.ui.theme.SongPlayerTheme
import com.unibz.songplayer.ui.PlaySongLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SongPlayerTheme( darkTheme = true ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PlaySongLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
