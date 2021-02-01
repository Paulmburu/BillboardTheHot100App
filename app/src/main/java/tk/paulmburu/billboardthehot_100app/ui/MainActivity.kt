package tk.paulmburu.billboardthehot_100app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tk.paulmburu.billboardthehot_100app.model.MusicSong
import tk.paulmburu.billboardthehot_100app.Adapters.MyAdapter
import tk.paulmburu.billboardthehot_100app.R
import tk.paulmburu.billboardthehot_100app.Repository.DataLoadListener
import tk.paulmburu.billboardthehot_100app.viewModels.MusicViewModel

class MainActivity : AppCompatActivity(), DataLoadListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = requireNotNull(this).application

        val viewModel = ViewModelProviders.of(this)[MusicViewModel(
            application
        )::class.java]

        viewManager = LinearLayoutManager(this)
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

    override fun onDataLoaded() {
        viewAdapter.notifyDataSetChanged()
    }

}
