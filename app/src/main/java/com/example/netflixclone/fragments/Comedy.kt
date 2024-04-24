package com.example.netflixclone.fragments

import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.netflixclone.R
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.databinding.FragmentComedyBinding
import com.example.netflixclone.databinding.FragmentDramaBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.movieadapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Comedy : Fragment() {

        private lateinit var binding:FragmentComedyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentComedyBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        var list=ArrayList<movies>()
        GlobalScope.launch {
            try {
                val apiservice=objectforapi.apiService
                val response=apiservice.getmoviecategory("Comedy")
                withContext(Dispatchers.Main){
                      binding.comedyrecylerview.adapter=movieadapter(requireContext(),response)
                }

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        Firebase.firestore.collection("videos").whereEqualTo("category","comedy").get()
//            .addOnSuccessListener {
//                for(document in it.documents){
//                    var moviename=document.getString("moviename")
//                    var moviedescription=document.getString("moviedescription")
//                    var movieimage=document.getString("imageurl")
//                    var movievideo=document.getString("downloadUrl")
//                    list.add(movies(moviename,moviedescription,movieimage,movievideo))
//                }
//               // binding.comedyrecylerview.adapter=movieadapter(requireContext(),list)
//
//            }
        return binding.root
    }


}