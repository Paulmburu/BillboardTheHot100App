package tk.paulmburu.billboardthehot_100app.Repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import tk.paulmburu.billboardthehot_100app.MusicSong
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import tk.paulmburu.billboardthehot_100app.DataLoadListener


class FirebaseRepository {

    var songs: List<MusicSong> = listOf()




    companion object {

//        var mContext: Context? = null
//        var dataLoadListener: DataLoadListener? = null

        @Volatile
        var INSTANCE: FirebaseRepository? = null

        fun getInstance(context: Context): FirebaseRepository {

//            mContext = context
            synchronized(this) {
                var instance = INSTANCE

                if (INSTANCE == null) {
                    instance = FirebaseRepository()
                    INSTANCE = instance
                }
//                dataLoadListener = mContext as DataLoadListener
                return instance!!
            }

        }
    }

    fun getSongs(): MutableLiveData<List<MusicSong>> {
        loadSongs()

        var song: MutableLiveData<List<MusicSong>> = MutableLiveData()
        song.value = songs

        return song
    }

    fun loadSongs(){
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Data")


        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (child in dataSnapshot.getChildren()) {
//                    val value = dataSnapshot.getValue(MusicSong::class.java)

//                    val value = child.getValue(MusicSong::class.java)
                    val value = child.value.hashCode()

                    Log.d(TAG, "Value is: " + value )
                }

//                dataLoadListener!!.onNameLoaded()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

}