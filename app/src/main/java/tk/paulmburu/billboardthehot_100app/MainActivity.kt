package tk.paulmburu.billboardthehot_100app

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = requireNotNull(this).application

        val viewModel = ViewModelProviders.of(this)[MusicViewModel(application)::class.java]

        var myDataset =
            listOf<MusicSong>(
                MusicSong("hello", "Buba"),
                MusicSong("Mandom", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"),
                MusicSong("hello", "Buba"),
                MusicSong("Mandong", "Odi wa muranga"))


        viewManager = LinearLayoutManager(this)
//        viewAdapter = MyAdapter(myDataset)
        viewAdapter = MyAdapter(viewModel.get_Songs())




        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }



    }
}
