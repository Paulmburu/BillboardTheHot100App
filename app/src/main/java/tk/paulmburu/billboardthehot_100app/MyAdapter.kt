package tk.paulmburu.billboardthehot_100app

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var data: LiveData<List<MusicSong>>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

//    var data = listOf<MusicSong>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    constructor(data: List<MusicSong>) : this() {
//        this.data = data
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.music_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = data.value!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.value!![position]
        val res = holder.itemView.context.resources
        holder.songName.text = item.songName.toString()
        holder.songArtists.text = item.songArtists.toString()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName: TextView = itemView.findViewById(R.id.song_name_id)
        val songArtists: TextView = itemView.findViewById(R.id.song_artist_id)
    }

}