package com.example.netflixclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.netflixclone.databinding.FragmentMainBinding

class main : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        val navhost=childFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navController=navhost!!.findNavController()
        val popupMenu= PopupMenu(requireContext(),null)
        popupMenu.inflate(R.menu.mainmenu)
        binding.mainbottomview.setupWithNavController(navController);
        return binding.root
    }


    }
