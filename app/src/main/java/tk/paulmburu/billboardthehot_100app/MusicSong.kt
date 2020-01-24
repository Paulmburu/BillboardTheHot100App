package tk.paulmburu.billboardthehot_100app

import com.google.firebase.database.PropertyName

data class MusicSong(
    @get:PropertyName("Artists") val songArtists: String = "",
    @get:PropertyName("Name") val songName: String = ""

    )
