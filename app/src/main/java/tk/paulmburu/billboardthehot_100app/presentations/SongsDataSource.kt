package tk.paulmburu.billboardthehot_100app.presentations

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tk.paulmburu.billboardthehot_100app.data.SongsRepository
import tk.paulmburu.billboardthehot_100app.data.models.RealtimeSong

class SongsDataSource(
    private val songsRepository: SongsRepository,
    private val scope: CoroutineScope
): ItemKeyedDataSource<String, RealtimeSong>() {

    class Factory(
        private val myRepository: SongsRepository,
        private val scope: CoroutineScope
    ) : DataSource.Factory<String, RealtimeSong>() {
        override fun create(): DataSource<String, RealtimeSong> =
            SongsDataSource(myRepository, scope)
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<RealtimeSong>
    ) {
        scope.launch {
            val items = songsRepository.getSongs(params.requestedLoadSize)
            callback.onResult(items)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RealtimeSong>) {
        scope.launch {
            val items = songsRepository.getSongs(params.requestedLoadSize, loadAfter = params.key)
            callback.onResult(items)
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RealtimeSong>) {
        scope.launch {
            val items = songsRepository.getSongs(params.requestedLoadSize, loadBefore = params.key)
            callback.onResult(items)
        }
    }

    override fun getKey(item: RealtimeSong) = item.id


}