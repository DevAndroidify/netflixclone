package com.example.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.databinding.ActivityMovieactivityBinding
import com.example.netflixclone.models.database
import com.example.netflixclone.models.downloadclass
import com.example.netflixclone.models.movies
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class movieactivity : AppCompatActivity() {
    private lateinit var entry: entry
    private lateinit var binding:ActivityMovieactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMovieactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

          val movietitle=intent.getStringExtra("movietitle")
        val moviedesc=intent.getStringExtra("moviedesc")
        val video=intent.getStringExtra("video")
        val image=intent.getStringExtra("image")
        binding.movietitle.text=movietitle
        binding.moviedescription.text=moviedesc
        val link="http://172.22.37.171:4000/"+video
        val linkuri=Uri.parse(link)
        binding.movievideoview.setVideoURI(linkuri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.movievideoview)
        binding.movievideoview.setMediaController(mediaController)
        binding.movieplay.setOnClickListener {
            binding.movievideoview.start()

        }
        binding.moviedownload.setOnClickListener {
            val database=database.getDatabase(this)
        GlobalScope.launch {
            try {
                database.cartdao().putdownload(downloadclass(0,movietitle!!,moviedesc!!,image!!,video!!))
                withContext(Dispatchers.Main){
                    Toast.makeText(this@movieactivity, "Added to downloads", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@movieactivity, "Failed to add to downloads", Toast.LENGTH_SHORT).show()
                }

            }
        }

        }
   val apiservice=objectforapi.apiService
        val list= arrayListOf<movies>()
        GlobalScope.launch {
            try {
             val response=apiservice.getmovie()
                withContext(Dispatchers.Main) {
                  binding.bottommovielist.adapter=movieadapter(this@movieactivity,response)

                }
            } catch (e: Exception) {
                   withContext(Dispatchers.Main){
                       Toast.makeText(this@movieactivity, "Failed to get movie", Toast.LENGTH_SHORT).show()
                   }
            }
        }
//        Firebase.firestore.collection("videos").get().addOnSuccessListener {
//            for(documents in it.documents){
//                val imageurl=documents.getString("imageurl")
//                val downloadurl=documents.getString("downloadUrl")
//                val moviename=documents.getString("moviename")
//                val moviedescription=documents.getString("moviedescription")
//
//                list.add(movies(moviename,moviedescription,imageurl,downloadurl))
//            }
            //binding.bottommovielist.adapter=movieadapter(this,list)

        }
    }

