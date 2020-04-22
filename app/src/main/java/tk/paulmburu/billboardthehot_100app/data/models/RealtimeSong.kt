package tk.paulmburu.billboardthehot_100app.data.models

import io.reactivex.rxjava3.core.Observable


data class RealtimeSong(
    val id: String,
    val song: Observable<Song>
)
