package com.example.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.core.net.toUri
import com.example.netflixclone.databinding.ActivityMovieactivityBinding
import com.example.netflixclone.models.movies
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

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
        binding.movietitle.text=movietitle
        binding.moviedescription.text=moviedesc
        binding.movievideoview.setVideoURI((Uri.parse(video)))
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.movievideoview)
        binding.movievideoview.setMediaController(mediaController)
        binding.movieplay.setOnClickListener {
            binding.movievideoview.start()

        }
        binding.moviedownload.setOnClickListener {
            Toast.makeText(this, "Movie added to downloads", Toast.LENGTH_SHORT).show()
        }

        val list= arrayListOf<movies>()
        Firebase.firestore.collection("videos").get().addOnSuccessListener {
            for(documents in it.documents){
                val imageurl=documents.getString("imageurl")
                val downloadurl=documents.getString("downloadUrl")
                val moviename=documents.getString("moviename")
                val moviedescription=documents.getString("moviedescription")

                list.add(movies(moviename,moviedescription,imageurl,downloadurl))
            }
            binding.bottommovielist.adapter=movieadapter(this,list)

        }
    }

}