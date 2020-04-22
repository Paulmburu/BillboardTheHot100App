package tk.paulmburu.billboardthehot_100app.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import tk.paulmburu.billboardthehot_100app.data.SongsRepository
import tk.paulmburu.billboardthehot_100app.data.models.RealtimeSong
import tk.paulmburu.billboardthehot_100app.presentations.SongsDataSource

class MusicViewModel(songsRepository: SongsRepository): ViewModel(){


private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPrefetchDistance(10)
        .setPageSize(20)
        .build()

    val records: LiveData<PagedList<RealtimeSong>> =
        LivePagedListBuilder<String, RealtimeSong>(
            SongsDataSource.Factory(songsRepository, uiScope),
            config
        ).build()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}