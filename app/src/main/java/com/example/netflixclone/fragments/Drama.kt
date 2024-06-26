package com.example.netflixclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.netflixclone.R
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.databinding.FragmentDramaBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.movieadapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Drama : Fragment() {
       private lateinit var binding:FragmentDramaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var list=ArrayList<movies>()
        binding=FragmentDramaBinding.inflate(layoutInflater)
        val apiservice=objectforapi.apiService
        GlobalScope.launch {
            try {
                val response=apiservice.getmoviecategory("Drama")
                withContext(Dispatchers.Main){
                     binding.dramarecylerview.adapter=movieadapter(requireContext(),response)
                 }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        Firebase.firestore.collection("videos").whereEqualTo("category","drama").get()
//            .addOnSuccessListener {
//                for(document in it.documents){
//                    var moviename=document.getString("moviename")
//                    var moviedescription=document.getString("moviedescription")
//                    var movieimage=document.getString("imageurl")
//                    var movievideo=document.getString("downloadUrl")
//                    list.add(movies(moviename,moviedescription,movieimage,movievideo))
//                }
//              //  binding.dramarecylerview.adapter=movieadapter(requireContext(),list)
//
//            }
        // Inflate the layout for this fragment
        return binding.root
    }


}