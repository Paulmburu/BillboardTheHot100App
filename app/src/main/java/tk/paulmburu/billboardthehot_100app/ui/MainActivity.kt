package tk.paulmburu.billboardthehot_100app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tk.paulmburu.billboardthehot_100app.R
import tk.paulmburu.billboardthehot_100app.data.SongsRepository
import tk.paulmburu.billboardthehot_100app.presentations.SongsAdapter

class MainActivity : AppCompatActivity() {

    lateinit var factory: MusicViewModelFactory
    lateinit var viewModel: MusicViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    val swipeSongs by lazy { findViewById<SwipeRefreshLayout>(R.id.swipe_songs)  }
    val recyclerViewSongs by lazy { findViewById<RecyclerView>(R.id.my_recycler_view) }
    val songsAdapter by lazy { SongsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factory = MusicViewModelFactory(SongsRepository())
        viewManager = LinearLayoutManager(this)

        recyclerViewSongs.apply {
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = songsAdapter
        }
        viewModel = ViewModelProviders.of(this, factory).get(MusicViewModel::class.java)

        viewModel.records.observe(this, Observer {
            swipeSongs.isRefreshing = false
            songsAdapter.submitList(it)
        })

    }
}
