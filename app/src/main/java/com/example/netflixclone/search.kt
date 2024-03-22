package com.example.netflixclone

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netflixclone.databinding.FragmentSearchBinding
import com.example.netflixclone.models.movies
import com.google.firebase.firestore.FirebaseFirestore

class search : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: searchadapter
    private val movieList = ArrayList<movies>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
        adapter = searchadapter(requireContext(), movieList)
        binding.searchrecylerview.layoutManager = LinearLayoutManager(requireContext())
        binding.searchrecylerview.adapter = adapter

        // Load data from Firestore
        loadMovies()

        // Set up SearchView
        binding.moviesearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText!!)
                return true
            }
        })

        return binding.root
    }

    private fun loadMovies() {
        val db = FirebaseFirestore.getInstance()
        db.collection("videos").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val imageurl = document.getString("imageurl")
                    val downloadurl = document.getString("downloadUrl")
                    val moviename = document.getString("moviename")
                    val moviedescription = document.getString("moviedescription")
                    movieList.add(movies(moviename, moviedescription, imageurl, downloadurl))
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle failures
            }
    }
}
