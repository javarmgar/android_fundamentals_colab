package com.example.androidfundamentalsapp.viewsActivity.dynamicviewsactivity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfundamentalsapp.databinding.LayoutRecyclerMovieItemBinding
import com.example.androidfundamentalsapp.viewsActivity.dynamicviewsactivity.DynamicViewsActivity.Companion.DYNAMIC_VIEW_LOG_TAG

class CustomRecyclerViewAdapter(
    private val recyclerAdapteeList: MutableList<Movie>
) :
    RecyclerView.Adapter<CustomRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerViewHolder {

       val binding: LayoutRecyclerMovieItemBinding = LayoutRecyclerMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Log.d(DYNAMIC_VIEW_LOG_TAG, "CustomRecyclerViewAdapter -> onCreateViewHolder()")
        return CustomRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomRecyclerViewHolder, position: Int) {
        Log.d(DYNAMIC_VIEW_LOG_TAG, "CustomRecyclerViewAdapter -> onBindViewHolder()")
        //adaptee
        val adaptee = recyclerAdapteeList[position]
        holder.bind(adaptee)
    }

    override fun getItemCount(): Int {
        return recyclerAdapteeList.size
    }
}

class CustomRecyclerViewHolder(private val binding: LayoutRecyclerMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(adaptee: Movie) {
        binding.textViewTitleValue.text = adaptee.title
        binding.textViewLength.text = adaptee.length.toString()
        binding.textViewRating.text = adaptee.rating.toString()
        binding.textViewYearValue.text = adaptee.year.toString()
    }

}
