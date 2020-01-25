package tk.paulmburu.billboardthehot_100app

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tk.paulmburu.billboardthehot_100app.Repository.FirebaseRepository

class MusicViewModel(application: Application): AndroidViewModel(application){
    var songs: MutableLiveData<List<MusicSong>>


    init {
//        songs?.let {
//                it -> FirebaseRepository.getInstance(application).getSongs()
//        }
        songs = FirebaseRepository.getInstance(application).getSongs()
    }

    fun get_Songs(): LiveData<List<MusicSong>>{
        return songs
    }


}