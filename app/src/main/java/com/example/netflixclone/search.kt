package com.example.netflixclone

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netflixclone.api.movieresponseItem
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.api.userapi
import com.example.netflixclone.databinding.FragmentSearchBinding
import com.example.netflixclone.models.movies
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class search : Fragment(){

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: searchadapter

    private  var response=ArrayList<movieresponseItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
//        adapter = searchadapter(requireContext(), movieList)
//        binding.searchrecylerview.layoutManager = LinearLayoutManager(requireContext())
//        binding.searchrecylerview.adapter = adapter

        // Load data from Firestore
        loadMovies()

        // Set up SearchView
        binding.moviesearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return binding.root
    }

    private fun loadMovies() {
//        val db = FirebaseFirestore.getInstance()
//        db.collection("videos").get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val imageurl = document.getString("imageurl")
//                    val downloadurl = document.getString("downloadUrl")
//                    val moviename = document.getString("moviename")
//                    val moviedescription = document.getString("moviedescription")
//                    movieList.add(movies(moviename, moviedescription, imageurl, downloadurl))
//                }
//                adapter.notifyDataSetChanged()
//            }
//            .addOnFailureListener { exception ->
//                // Handle failures
//            }
        val apiservice=objectforapi.apiService
        GlobalScope.launch {
            try {
                response=apiservice.getmovie()
                withContext(Dispatchers.Main){
                    binding.searchrecylerview.adapter=searchadapter(requireContext(),response)

                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    private fun filterList(query: String?) {

        if (query != null) {

            val filteredList = ArrayList<movieresponseItem>()
            for (i in response) {
                val movie=i.moviename.trim('"')
                if (movie.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
            } else {
                binding.searchrecylerview.adapter=searchadapter(requireContext(), filteredList)


            }
        }
    }


}
