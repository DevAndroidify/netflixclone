package com.example.netflixclone

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflixclone.api.movieresponseItem
import com.example.netflixclone.models.movies

class movieadapter(private val context: Context,private val entryList:ArrayList<movieresponseItem>) :
    RecyclerView.Adapter<movieadapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviesrecylerlayout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val innerList =entryList[position] // Access the note at the given position
           val main=innerList.moviename.trim('"')
        val second=innerList.moviedescription.trim('"')
        val third=innerList.movievideo.replace("\\","/")


        Glide.with(context).load("http://172.22.37.171:4000/"+innerList.movieimage.replace("\\","/")).into(holder.thirdimage)

        holder.itemView.setOnClickListener {

            val a=Intent(context,movieactivity::class.java)
             a.putExtra("movietitle",main)
            a.putExtra("moviedesc",second)
            a.putExtra("video",third)
            a.putExtra("image",innerList.movieimage)
            context.startActivity(a)
        }


    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val thirdimage:ImageView=itemView.findViewById(R.id.movieimage)
    }
}
