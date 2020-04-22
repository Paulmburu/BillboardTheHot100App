package tk.paulmburu.billboardthehot_100app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tk.paulmburu.billboardthehot_100app.data.SongsRepository

class MusicViewModelFactory(
    private val songsRepository: SongsRepository
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            return MusicViewModel(songsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}