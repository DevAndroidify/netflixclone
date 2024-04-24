package com.example.netflixclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.netflixclone.databinding.FragmentDownloadsBinding
import com.example.netflixclone.models.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class downloads : Fragment() {
  private lateinit var binding:FragmentDownloadsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentDownloadsBinding.inflate(layoutInflater)
        val database=database.getDatabase(requireContext())
        GlobalScope.launch {
            try{
                val response=database.cartdao().getdownload()
                withContext(Dispatchers.Main){
                    binding.downloadrecylerview.adapter=downloadadapter(requireContext(),response)
                }
            }catch (e:Exception){
             withContext(Dispatchers.Main){
                 Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
             }

            }

        }
        binding.downloadbackarrow.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.signoutbutton.setOnClickListener {
             findNavController().navigate(R.id.action_downloads_to_mainActivity)

        }
        return binding.root
    }


}