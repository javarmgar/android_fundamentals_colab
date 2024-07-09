package com.example.androidfundamentalsapp.viewsActivity.dynamicviewsactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.androidfundamentalsapp.R


class CustomAdapter(private val context: Context, private val adapteeList: List<Movie>) : BaseAdapter(){
    override fun getCount(): Int {
        return adapteeList.size
    }

    override fun getItem(position: Int): Any {
        return adapteeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        //adaptee
        val adaptee: Movie = adapteeList[position]

        //target
        val target: View = view?:LayoutInflater.from(context).inflate(R.layout.layout_movie_item,parent, false)

        //adapting...
        val title = target.findViewById<TextView>(R.id.text_view_title_value)
        val year = target.findViewById<TextView>(R.id.text_view_year_value)
        val rating = target.findViewById<TextView>(R.id.text_view_rating_value)

        title.text = adaptee.title
        year.text = adaptee.year.toString()
        rating.text = adaptee.rating.toString()
        return target
    }

}
