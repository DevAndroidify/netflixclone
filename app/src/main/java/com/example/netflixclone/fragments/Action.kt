package com.example.netflixclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netflixclone.R
import com.example.netflixclone.databinding.FragmentActionBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.movieadapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Action : Fragment() {

        private lateinit var binding:FragmentActionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentActionBinding.inflate(layoutInflater)
        var list=ArrayList<movies>()
        Firebase.firestore.collection("videos").whereEqualTo("category","action").get()
            .addOnSuccessListener {
                for(document in it.documents){
                    var moviename=document.getString("moviename")
                    var moviedescription=document.getString("moviedescription")
                    var movieimage=document.getString("imageurl")
                    var movievideo=document.getString("downloadUrl")
                    list.add(movies(moviename,moviedescription,movieimage,movievideo))
                }
                binding.actionrecylerview.adapter= movieadapter(requireContext(),list)

            }
        return binding.root
    }

}