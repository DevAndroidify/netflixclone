package com.example.netflixclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflixclone.models.database
import com.example.netflixclone.models.downloadclass
import com.example.netflixclone.models.movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class downloadadapter(private val context: Context, private var entryList: List<downloadclass>) :
    RecyclerView.Adapter<downloadadapter.NoteViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.downloadrecyler, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val innerList = entryList[position] // Access the movie at the given position
        val main = innerList.moviename
        val second = innerList.moviedescription
        val third = innerList.moviepath
        holder.text.text = main
        Glide.with(context).load("http://172.22.37.171:4000/"+innerList.imagepath.replace("\\","/")).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, movieactivity::class.java)
            intent.putExtra("movietitle", main)
            intent.putExtra("moviedesc", second)
            intent.putExtra("video", third)
            context.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            GlobalScope.launch {
                try {
                    database.getDatabase(context).cartdao().deletedata(downloadclass(id=innerList.id,main,second,innerList.imagepath,third))
                    withContext(Dispatchers.Main){
                        notifyDataSetChanged()

                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.searchmoviename)
        val image: ImageView = itemView.findViewById(R.id.searchmovieimage)
        val delete:ImageView=itemView.findViewById(R.id.downloaddelete)
    }


}
