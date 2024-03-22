package com.example.netflixclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netflixclone.databinding.FragmentFastlaughBinding
import com.example.netflixclone.models.movies
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class fastlaugh : Fragment() {
  private lateinit var binding:FragmentFastlaughBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFastlaughBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val list= arrayListOf<movies>()
        Firebase.firestore.collection("videos").get().addOnSuccessListener {
            for(documents in it.documents){
                val imageurl=documents.getString("imageurl")
                val downloadurl=documents.getString("downloadUrl")
                val moviename=documents.getString("moviename")
                val moviedescription=documents.getString("moviedescription")

                list.add(movies(moviename,moviedescription,imageurl,downloadurl))
            }
            binding.fashandlaughrecylerview.adapter=movieadapter(requireContext(),list)

        }

        return binding.root
    }

}