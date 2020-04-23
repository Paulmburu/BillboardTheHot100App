package tk.paulmburu.billboardthehot_100app.data

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.tasks.await
import tk.paulmburu.billboardthehot_100app.data.models.RealtimeSong
import tk.paulmburu.billboardthehot_100app.data.models.Song
import javax.inject.Inject

class SongsRepository @Inject constructor() {
    val db = FirebaseFirestore.getInstance()

    suspend fun getSongs(
        pagesize: Int,
        loadBefore: String? = null,
        loadAfter: String? = null
    ): List<RealtimeSong>{
        var query = db.collection("data").limit(pagesize.toLong())

        loadBefore?.let {
            val item = db.collection("data").document(it)
                .get()
                .await()

            query = query.endBefore(item)
        }

        loadAfter?.let {
            val song = db.collection("data").document(it)
                .get()
                .await()

            query = query.startAfter(song)
        }

        return query.get()
            .await()
            .map {
                RealtimeSong(
                    it.id,
                    getSong(it.id)
                )
            }
    }

    private fun getSong(songId: String): Observable<Song> =
        Observable.create<Song>{ emitter ->
            db.collection("data")
                .document(songId)
                .addSnapshotListener{ snapshot, exception ->
                    if(exception != null){
                        emitter.onError(exception)
                    }else if(snapshot != null && snapshot.exists()){
                        emitter.onNext(
                            snapshot.toObject(Song::class.java) ?: throw IllegalArgumentException()
                        )
                    }else{
                        emitter.onError(Throwable("Song does not exist"))
                    }
                }
    }
}