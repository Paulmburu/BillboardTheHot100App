package tk.paulmburu.billboardthehot_100app.data.models

data class Song(
    @Transient
    val songArtists: String = "",
    val songName: String = "",
    val isActive: Boolean = false
)
