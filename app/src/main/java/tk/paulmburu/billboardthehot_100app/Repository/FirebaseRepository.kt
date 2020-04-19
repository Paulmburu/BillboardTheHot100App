package tk.paulmburu.billboardthehot_100app.Repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import tk.paulmburu.billboardthehot_100app.model.MusicSong


class FirebaseRepository {
    // Write a message to the database
    val database = FirebaseDatabase.getInstance()

    fun getBillboardSongs(): DatabaseReference{
        var databaseReference = database.getReference("Data")
        return databaseReference
    }


}