package tk.paulmburu.billboardthehot_100app.ui

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tk.paulmburu.billboardthehot_100app.Repository.FirebaseRepository
import tk.paulmburu.billboardthehot_100app.data.models.Song

class MusicViewModel(application: Application): AndroidViewModel(application){


    val TAG = "MUSIC_VIEW_MODEL"
    var firebaseRepository = FirebaseRepository()

    val database = FirebaseDatabase.getInstance()


    var songs: MutableLiveData<List<Song>> = MutableLiveData()

    fun getSongsUpdate():LiveData<List<Song>>{
        Log.d(TAG,"firebase")
        var currentSongs: MutableList<Song> = mutableListOf()
        firebaseRepository.getBillboardSongs().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

//                val post = dataSnapshot.getValue()

                Log.d(TAG, "out for")
                for (child in dataSnapshot.getChildren()) {
                    Log.d(TAG, "in for")
                    var artist_child: String = child.child("Artists").getValue().toString()
                    var name_child: String = child.child("Name").getValue().toString()

                    Log.d(TAG, "Value is: ")

                    currentSongs.add(
                        Song(
                            artist_child,
                            name_child
                        )
                    )


                    Log.d(TAG, "Value is: {$name_child} == {$artist_child}")
                }
//                Log.d(TAG,currentSongs.toString())
                songs.value = currentSongs

            }




            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.d(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
//        Log.d(TAG,currentSongs.toString())
        return songs
    }


//    suspend fun getMySongs(): DataSnapshot?{
//        return  try {
//            val data = database.getReference("Data").addValueEventListener()
//        }
//
//    }


    init {
        Log.d(TAG,"We are in")
       getSongsUpdate()
    }
//
//    fun get_Songs(): LiveData<List<MusicSong>>{
//        return songs
//    }


}