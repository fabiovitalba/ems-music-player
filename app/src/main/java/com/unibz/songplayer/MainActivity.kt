package com.unibz.songplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import com.unibz.songplayer.ui.theme.SongPlayerTheme

val deusCulpaGhost = Song("Deus Culpa", "Ghost")
val conClaviConDio = Song("Con Clavi Con Dio","Ghost")
val ritualGhost = Song("Ritual","Ghost")
val opusEponymousSongList = listOf(
    deusCulpaGhost,
    conClaviConDio,
    ritualGhost
)
val opusEponymousGhost = Album("Opus Eponymous","Ghost",opusEponymousSongList,R.drawable.ghost_opus_eponymous)

val meineKneipeVonWegenLisbeth = Song("Meine Kneipe","Von Wegen Lisbeth")
val cherieVonWegenLisbeth = Song("Chérie","Von Wegen Lisbeth")
val wennDuTanztVonWegenLisbeth = Song("Wenn du tanzt","Von Wegen Lisbeth")
val grandeSongList = listOf(
    meineKneipeVonWegenLisbeth,
    cherieVonWegenLisbeth,
    wennDuTanztVonWegenLisbeth
)
val grandeVonWegenLisbeth = Album("GRANDE","Von Wegen Lisbeth",grandeSongList,R.drawable.von_wegen_lisbeth_grande)

val wiesoVonWegenLisbeth = Song("Wieso","Von Wegen Lisbeth")
val lieferandomannVonWegenLisbeth = Song("Lieferandomann","Von Wegen Lisbeth")
val alexaGibMirMeinGeldZurueckVonWegenLisbeth = Song("Alexa gib mir mein Geld zurück!","Von Wegen Lisbeth")
val sweetLilly93AtHotmailDotComSongList = listOf(
    wiesoVonWegenLisbeth,
    lieferandomannVonWegenLisbeth,
    alexaGibMirMeinGeldZurueckVonWegenLisbeth
)
val sweetLilly93AtHotmailDotComVonWegenLisbeth = Album("sweetlilly93@hotmail.com","Von Wegen Lisbeth",sweetLilly93AtHotmailDotComSongList,R.drawable.von_wegen_lisbeth_sweetlilly93_at_hotmail_com)

val heavyIsTheCrownMikeShinoda = Song("Heavy Is The Crown","Mike Shinoda,Emily Armstrong")
val iCantHearItNowFreyaRidings = Song("I Can't Hear It Now","Freya Ridings")
val suckerMarcusKing = Song("Sucker","Marcus King")
val arcaneSongList = listOf(
    heavyIsTheCrownMikeShinoda,
    iCantHearItNowFreyaRidings,
    suckerMarcusKing
)
val arcaneLeagueOfLegends = Album("Arcane League of Legends: Season 2 (Soundtrack)","Various",arcaneSongList,R.drawable.league_of_legends_arcane_season_two_soundtrack)

val albums = listOf(
    opusEponymousGhost,
    grandeVonWegenLisbeth,
    sweetLilly93AtHotmailDotComVonWegenLisbeth,
    arcaneLeagueOfLegends
)

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

@Composable
fun PlaySongLayout(modifier: Modifier = Modifier) {
    // App State
    var songId by remember { mutableIntStateOf(0) }
    var albumId by remember { mutableIntStateOf(0) }
    var songProgress by remember { mutableFloatStateOf(0.0f) }

    val album = albums[albumId]
    val song = album.songs[songId]

    Box(modifier = Modifier
        /*.background(brush = gradientBrush)*/
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                OutlinedButton(onClick = { /* TODO Go to Album Selection */ }) {
                    Text("Go Back"/* stringResource(R.string.next) */)
                }
            }
            Button(onClick = { /* TODO Go to Album Song List */ }) {
                Image(
                    painter = painterResource(album.albumArt),
                    contentDescription = "sunrise image",
                    modifier = Modifier
                        .height(250.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 8.dp,
                                topStart = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp
                            )
                        ),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { songProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = song.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = song.artist,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = album.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium
            )
            Row {
                FilledTonalButton(onClick = { /* TODO Previous song */ }) {
                    Text("PREV"/* stringResource(R.string.next) */)
                }
                Button(onClick = { /* TODO Start/Stop Song */ }) {
                    Text("PLAY/PAUSE"/* stringResource(R.string.next) */)
                }
                FilledTonalButton(onClick = { /* TODO Next song */ }) {
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
        PlaySongLayout()
    }
}