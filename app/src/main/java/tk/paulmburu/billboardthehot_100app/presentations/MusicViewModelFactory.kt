package tk.paulmburu.billboardthehot_100app.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tk.paulmburu.billboardthehot_100app.data.SongsRepository
import javax.inject.Inject

class MusicViewModelFactory @Inject constructor(
    private val songsRepository: SongsRepository
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            return MusicViewModel(
                songsRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}