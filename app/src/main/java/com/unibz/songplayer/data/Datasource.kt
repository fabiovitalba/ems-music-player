package com.unibz.songplayer.data

import com.unibz.songplayer.R

class Datasource {
    fun loadAlbums(): List<Album> {
        return listOf(
            Album("Opus Eponymous","Ghost",listOf(
                Song("Deus Culpa", "Ghost",93),
                Song("Con Clavi Con Dio","Ghost",211),
                Song("Ritual","Ghost",268)
            ), R.drawable.ghost_opus_eponymous),
            Album("GRANDE","Von Wegen Lisbeth",listOf(
                Song("Meine Kneipe","Von Wegen Lisbeth",182),
                Song("Chérie","Von Wegen Lisbeth",177),
                Song("Komm mal rüber bitte","Von Wegen Lisbeth",246),
                Song("Wenn du tanzt","Von Wegen Lisbeth",230)
            ), R.drawable.von_wegen_lisbeth_grande),
            Album("sweetlilly93@hotmail.com","Von Wegen Lisbeth",listOf(
                Song("Wieso","Von Wegen Lisbeth",190),
                Song("Lieferandomann","Von Wegen Lisbeth",200),
                Song("Alexa gib mir mein Geld zurück!","Von Wegen Lisbeth",124)
            ), R.drawable.von_wegen_lisbeth_sweetlilly93_at_hotmail_com),
            Album("Arcane League of Legends: Season 2 (Soundtrack)","Various",listOf(
                Song("Heavy Is The Crown","Mike Shinoda,Emily Armstrong",101),
                Song("I Can't Hear It Now","Freya Ridings",160),
                Song("Sucker","Marcus King",224),
                Song("Renegade (We Never Run)","Raja Kumari,Stefflon Don,Jarina De Marco",160),
                Song("To Ashes and Blood","Woodkid",164),
                Song("Come Play","Stray Kids,Young Miko,Tom Morello",245)
            ),R.drawable.league_of_legends_arcane_season_two_soundtrack)
        )
    }
}
