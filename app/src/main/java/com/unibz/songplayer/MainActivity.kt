package com.unibz.songplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibz.songplayer.ui.theme.SongPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SongPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlaySong()
                }
            }
        }
    }
}

@Composable
fun PlaySong() {
    Column {
        Image(
            painter = painterResource(R.drawable.von_wegen_lisbeth_grande),
            contentDescription = "sunrise image",
            modifier = Modifier
                .height(180 .dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "Von Wegen Lisbeth - Wenn du tanzt"
        )
        Text(
            text = "GRANDE"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaySongPreview() {
    SongPlayerTheme {
        PlaySong()
    }
}