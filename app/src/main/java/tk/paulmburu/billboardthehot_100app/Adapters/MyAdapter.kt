package tk.paulmburu.billboardthehot_100app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import tk.paulmburu.billboardthehot_100app.R
import tk.paulmburu.billboardthehot_100app.model.MusicSong

class MyAdapter(var data: LiveData<List<MusicSong>>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

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


//        Glide.with(holder.songImageView.context)
//            .load(when(position){
//                0 -> R.drawable.s_1
//                1 -> R.drawable.s_2
//                2 -> R.drawable.s_2
//                3 -> R.drawable.four
//                4 -> R.drawable.five
//                5 -> R.drawable.six
//                6 -> R.drawable.seven
//                7 -> R.drawable.eight
//                8 -> R.drawable.nine
//                9 -> R.drawable.ten
//                10 -> R.drawable.eleven
//                11 -> R.drawable.twelve
//                else -> R.drawable.bb
//            })
//            .placeholder(R.drawable.bb)
//            .transform(RoundedCorners(holder.songImageView.resources.getDimensionPixelSize(R.dimen.corner_radius)))
//            .dontAnimate()
//            .into(holder.songImageView)

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName: TextView = itemView.findViewById(R.id.song_name_id)
        val songArtists: TextView = itemView.findViewById(R.id.song_artist_id)
//        val songImageView: ImageView = itemView.findViewById(R.id.song_image_id)
    }

}