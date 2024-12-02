package com.unibz.songplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibz.songplayer.ui.theme.SongPlayerTheme

val wennDuTanztVonWegenLisbeth = Song("Wenn du tanzt","Von Wegen Lisbeth","GRANDE",R.drawable.von_wegen_lisbeth_grande)
val alexaGibMirMeinGeldZurueckVonWegenLisbeth = Song("Alexa gib mir mein Geld zurück!","Von Wegen Lisbeth","sweetlilly93@hotmail.com",R.drawable.von_wegen_lisbeth_sweetlilly93_at_hotmail_com)
val ritualGhost = Song("Ritual","Ghost","Opus Eponymous",R.drawable.ghost_opus_eponymous)
val suckerMarcusKing = Song("Sucker","Marcus King","", R.drawable.league_of_legends_arcane_season_two_soundtrack)

val gradientBrush =
    Brush.verticalGradient(
        colors = listOf(Color.Red, Color.Blue, Color.Green),
        startY = 0.0f,
        endY = 3000.0f
    )

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SongPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlaySong(
                        song = ritualGhost,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PlaySong(song: Song, modifier: Modifier = Modifier) {
    var songId by remember { mutableStateOf(0) } // state value
    Box(modifier = Modifier
        .background(brush = gradientBrush)
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        Column( horizontalAlignment = Alignment.CenterHorizontally ) {
            Image(
                painter = painterResource(song.darwableResource),
                contentDescription = "sunrise image",
                modifier = Modifier
                    .height(180.dp)
                    .clip(RoundedCornerShape(topEnd = 8.dp , topStart = 8.dp, bottomStart = 8.dp, bottomEnd = 8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = song.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = song.artist,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 8.sp
            )
            Text(
                text = song.album,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 6.sp
            )
            Row {
                Button(onClick = { /* TODO */ }) {
                    Text("PREV"/* stringResource(R.string.next) */)
                }
                Button(onClick = { /* TODO */ }) {
                    Text("PLAY/PAUSE"/* stringResource(R.string.next) */)
                }
                Button(onClick = { /* TODO */ }) {
                    Text("NEXT"/* stringResource(R.string.next) */)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaySongPreview() {
    SongPlayerTheme {
        PlaySong(
            song = ritualGhost
        )
    }
}