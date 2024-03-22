package com.example.netflixclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflixclone.models.movies
import java.util.*
import kotlin.collections.ArrayList

class searchadapter(private val context: Context, private var entryList: ArrayList<movies>) :
    RecyclerView.Adapter<searchadapter.NoteViewHolder>() {

    private var filteredList: ArrayList<movies> = ArrayList()

    init {
        filteredList = entryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searchrecyler, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val innerList = filteredList[position] // Access the movie at the given position
        val main = innerList.maintext
        val second = innerList.secondtext
        val third = innerList.video
        holder.text.text = main
        Glide.with(context).load(innerList.image).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, movieactivity::class.java)
            intent.putExtra("movietitle", main)
            intent.putExtra("moviedesc", second)
            intent.putExtra("video", third)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.searchmoviename)
        val image: ImageView = itemView.findViewById(R.id.searchmovieimage)
    }

    fun filter(charText: String) {
        val searchText = charText.toLowerCase(Locale.getDefault())
        filteredList.clear()
        if (searchText.isEmpty()) {
            filteredList.addAll(entryList)
        } else {
            for (movie in entryList) {
                if (movie.maintext!!.toLowerCase(Locale.getDefault()).contains(searchText)) {
                    filteredList.add(movie)
                }
            }
        }
        notifyDataSetChanged()
    }
}
