package com.example.netflixclone.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.netflixclone.R
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.databinding.FragmentActionBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.movieadapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Action : Fragment() {

        private lateinit var binding:FragmentActionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentActionBinding.inflate(layoutInflater)
        var list=ArrayList<movies>()
        val apiservice=objectforapi.apiService
        GlobalScope.launch {
            try {
                val response=apiservice.getmoviecategory("Action")

                withContext(Dispatchers.Main){
                    binding.actionrecylerview.adapter=movieadapter(requireContext(),response)
                }

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    Log.d( "onCreateView: ",e.message.toString())

                }
            }
        }
//        Firebase.firestore.collection("videos").whereEqualTo("category","action").get()
//            .addOnSuccessListener {
//                for(document in it.documents){
//                    var moviename=document.getString("moviename")
//                    var moviedescription=document.getString("moviedescription")
//                    var movieimage=document.getString("imageurl")
//                    var movievideo=document.getString("downloadUrl")
//                    list.add(movies(moviename,moviedescription,movieimage,movievideo))
//                }
//               // binding.actionrecylerview.adapter= movieadapter(requireContext(),list)
//
//            }
        return binding.root
    }

}