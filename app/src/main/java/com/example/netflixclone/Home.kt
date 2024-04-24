package com.example.netflixclone

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.databinding.FragmentHomeBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.models.slidemodel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Home : Fragment() {
          private  lateinit var stname:Array<String>
                 private  lateinit var stdesc:Array<String>
                 private lateinit  var stvideo:Array<String>
           private lateinit  var binding:FragmentHomeBinding
           private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        val list= arrayListOf<movies>()
        stname = arrayOf("", "", "") // Initialize with appropriate size and default values
        stdesc = arrayOf("", "", "") // Initialize with appropriate size and default values
        stvideo = arrayOf("", "", "") 

        val db=Firebase.firestore
         sharedPreferences=requireContext().getSharedPreferences("netflixuser", Context.MODE_PRIVATE)
        val token=sharedPreferences.getString("token",null)
//        db.collection("videos").get().addOnSuccessListener {
//
//            stname[0] = it.documents[13].getString("moviename")!!
//            stdesc[0] = it.documents[13].getString("moviedescription")!!
//            stvideo[0] = it.documents[13].getString("downloadUrl")!!
//            stname[1] = it.documents[1].getString("moviename")!!
//            stdesc[1] = it.documents[1].getString("moviedescription")!!
//            stvideo[1] = it.documents[1].getString("downloadUrl")!!
//            stname[2] = it.documents[14].getString("moviename")!!
//            stdesc[2] = it.documents[14].getString("moviedescription")!!
//            stvideo[2] = it.documents[14].getString("downloadUrl")!!
//            for (documents in it.documents) {
//                val imageurl = documents.getString("imageurl")
//                val downloadurl = documents.getString("downloadUrl")
//                val moviename = documents.getString("moviename")
//                val moviedescription = documents.getString("moviedescription")
//                list.add(movies(moviename, moviedescription, imageurl, downloadurl))
//
//            }
//            binding.movierecylerview.adapter = movieadapter(requireContext(), list)
//        }
        val apiservice=objectforapi.apiService
        GlobalScope.launch {
            try {
                val response=apiservice.getmovie()
                withContext(Dispatchers.Main){
                    binding.movierecylerview.adapter=movieadapter(requireContext(),response)

                }

            }catch(e:Exception){

            }

        }
        var index=0;
        val imageList = ArrayList<SlideModel>() // Create image list
        GlobalScope.launch {
            try {
                val apiservice = objectforapi.apiService
                val response = apiservice.getthreemovie()
                for (i in response) {
                    withContext(Dispatchers.Main) {
                        stname[index] = i.moviename.trim('"')
                        stdesc[index] = i.moviedescription.trim('"')
                        stvideo[index] = i.movievideo
                        imageList.add(SlideModel("http://172.22.37.171:4000/" + i.movieimage.replace("\\", "/")))
                        index++
                    }
                }

                // Set the image list and item click listener after data is fetched
                withContext(Dispatchers.Main) {
                    binding.viewpager2.setImageList(imageList)
                    binding.viewpager2.setItemClickListener(object : ItemClickListener {
                        override fun doubleClick(position: Int) {
                            // Implement double click logic if needed
                        }

                        override fun onItemSelected(position: Int) {
                            // Implement item click logic here using stname, stdesc, and stvideo arrays
                            val a = Intent(requireContext(), movieactivity::class.java)
                            a.putExtra("movietitle", stname[position])
                            a.putExtra("moviedesc", stdesc[position])
                            a.putExtra("video", stvideo[position])
                            startActivity(a)
                        }
                    })
                }
            } catch (e: Exception) {
                // Handle exceptions here
            }
        }


        return binding.root
    }


}