package com.example.androidfundamentalsapp.viewsActivity.dynamicviewsactivity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfundamentalsapp.databinding.ActivityDynamicViewsBinding

class DynamicViewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicViewsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDynamicViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSimpleListView()
        setCustomListView()
        setRecyclerView()
    }



    private fun setSimpleListView() {
        //adaptee items
        val listMovieNames = listOf( /*adaptee*/"Up", "inside out", "rocky I", "Transformers", "Rambo", "Fight Club", "once upon a time in hollywood", "shawshank redemption")

        // target
        val target = android.R.layout.simple_list_item_1

        //adapter
        val adapter = ArrayAdapter(this, /*target*/ target, /*adaptee items*/ listMovieNames)

        binding.listViewSimple.adapter = adapter
    }

    private fun setCustomListView() {

        //adaptee items
        val adapteeItems = getRecyclerAdapteeList()

        //adapter
        val adapter = CustomAdapter(this,adapteeItems)

        //listView

        binding.listViewCustom.adapter = adapter
    }

    private fun getAdapteeList():List<Movie> {
        return listOf(
            Movie(
                title = "Up",
                year = 2007,
                rating = 7,
                length = 98,
            ),
            Movie(
                title = "Once upon a time in Hollywood",
                year = 2019,
                rating = 9,
                length = 132,
            ),
            Movie(
                title = "Rocky I",
                year = 1974,
                rating = 10,
                length = 111,
            ),
            Movie(
                title = "Shawshank Redemption",
                year = 1994,
                rating = 11,
                length = 144,
            )
        )
    }


// Recycler View Example

    private fun getRecyclerAdapteeList(): MutableList<Movie> {
        val adapteeList = getAdapteeList()

        val recyclerAdapteeList = mutableListOf<Movie>()
        for( i in 1..10000){
            val randomItem = (0..3).random()
            recyclerAdapteeList.add(adapteeList[randomItem])
        }
        return recyclerAdapteeList
    }
    private fun setRecyclerView() {
        //AdapteeList
        val recyclerAdapteeList = getRecyclerAdapteeList()

        // Adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter(recyclerAdapteeList)

    }

    companion object{
        val DYNAMIC_VIEW_LOG_TAG = "DYNAMIC_VIEW_LOG_TAG"
    }

}
