package com.example.netflixclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.netflixclone.databinding.FragmentEntryBinding



class entry : Fragment() {
    private lateinit var binding:FragmentEntryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEntryBinding.inflate(layoutInflater)
        binding.getstarted.setOnClickListener {
            findNavController().navigate(R.id.action_entry_to_register)
        }
        var list= ArrayList<entryrecyler>()
        list.add(entryrecyler("Unlimated entertainment one low price","All of Netflix,starting at just 149",R.drawable.blade4))
        list.add(entryrecyler("Download and watch offline","Always have something to watch",R.drawable.blade))
        list.add(entryrecyler("Cancel online at any time","Join today,no reason to watch",R.drawable.blade2))
        list.add(entryrecyler("Watch everywhere","Stream on your phone,tablet,laptop,TV and more.",R.drawable.blade4))

        binding.viewPager.adapter=adapter(requireContext(),list)
        binding.signin.setOnClickListener {
            findNavController().navigate(R.id.action_entry_to_signin)
        }
        return binding.root
    }


}