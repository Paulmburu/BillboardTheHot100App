package tk.paulmburu.billboardthehot_100app.presentations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import tk.paulmburu.billboardthehot_100app.R
import tk.paulmburu.billboardthehot_100app.data.models.RealtimeSong

class SongsAdapter : PagedListAdapter<RealtimeSong, SongsAdapter.SongViewHolder>(
    object : DiffUtil.ItemCallback<RealtimeSong>() {
        override fun areItemsTheSame(old: RealtimeSong, new: RealtimeSong): Boolean =
            old.id == new.id

        override fun areContentsTheSame(old: RealtimeSong, new: RealtimeSong): Boolean =
            old == new
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.music_item,
            parent,
            false
        )
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = getItem(position)
        holder.bind(song)
    }

    override fun onViewRecycled(holder: SongViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            song_artists.text = ""
            song_name.text = ""
            viewHolderDisposables.clear()
        }
    }

    inner class SongViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val viewHolderDisposables = CompositeDisposable()

        val song_artists by lazy { view.findViewById<TextView>(R.id.song_artist_id) }
        val song_name by lazy { view.findViewById<TextView>(R.id.song_name_id) }

        fun bind( realtimeSong: RealtimeSong?){
            realtimeSong?.let {
                it.song
                    .subscribeBy(
                        onNext = {
                            song_artists.text = it.songArtists
                            song_name.text = it.songName
                        }
                    )
                    .addTo(viewHolderDisposables)
            }
        }
    }

}