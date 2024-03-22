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
import kotlinx.coroutines.withContext

class adapter(private val context: Context,private val entryList:ArrayList<entryrecyler>) :
    RecyclerView.Adapter<adapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entryrecyler, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val innerList =entryList[position] // Access the note at the given position
        holder.Maintext.text = innerList.maintext
        holder.Secondtext.text = innerList.secondtext
        Glide.with(context).load(innerList.image).into(holder.thirdimage)


    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Maintext: TextView = itemView.findViewById(R.id.entrymaintext)
        val Secondtext: TextView = itemView.findViewById(R.id.entrysecondtext)
        val thirdimage:ImageView=itemView.findViewById(R.id.entryimage)
    }
}
