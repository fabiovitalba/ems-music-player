package com.unibz.songplayer.data

import com.unibz.songplayer.Album
import com.unibz.songplayer.R
import com.unibz.songplayer.Song

class Datasource {
    fun loadAlbums(): List<Album> {
        return listOf(
            Album("Opus Eponymous","Ghost",listOf(
                Song("Deus Culpa", "Ghost"),
                Song("Con Clavi Con Dio","Ghost"),
                Song("Ritual","Ghost")
            ), R.drawable.ghost_opus_eponymous),
            Album("GRANDE","Von Wegen Lisbeth",listOf(
                Song("Meine Kneipe","Von Wegen Lisbeth"),
                Song("Chérie","Von Wegen Lisbeth"),
                Song("Wenn du tanzt","Von Wegen Lisbeth")
            ), R.drawable.von_wegen_lisbeth_grande),
            Album("sweetlilly93@hotmail.com","Von Wegen Lisbeth",listOf(
                Song("Wieso","Von Wegen Lisbeth"),
                Song("Lieferandomann","Von Wegen Lisbeth"),
                Song("Alexa gib mir mein Geld zurück!","Von Wegen Lisbeth")
            ), R.drawable.von_wegen_lisbeth_sweetlilly93_at_hotmail_com),
            Album("Arcane League of Legends: Season 2 (Soundtrack)","Various",listOf(
                Song("Heavy Is The Crown","Mike Shinoda,Emily Armstrong"),
                Song("I Can't Hear It Now","Freya Ridings"),
                Song("Sucker","Marcus King")
            ),R.drawable.league_of_legends_arcane_season_two_soundtrack)
        )
    }
}
