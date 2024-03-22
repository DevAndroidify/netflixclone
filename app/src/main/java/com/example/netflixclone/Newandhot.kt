package com.example.netflixclone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.netflixclone.databinding.FragmentNewandhotBinding
import com.example.netflixclone.models.fragmentadapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Newandhot : Fragment() {
      private lateinit var adapter:fragmentadapter
     private lateinit var binding:FragmentNewandhotBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNewandhotBinding.inflate(layoutInflater)

         adapter= fragmentadapter(requireActivity().supportFragmentManager,lifecycle)
          binding.tablayout.addTab(binding.tablayout.newTab().setText("Action"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Comedy"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Drama"))
        binding.newandhotviewpager.adapter=adapter
           binding.tablayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
               override fun onTabSelected(tab: TabLayout.Tab?) {
                   binding.newandhotviewpager.currentItem=tab!!.position
               }

               override fun onTabUnselected(tab: TabLayout.Tab?) {
               }

               override fun onTabReselected(tab: TabLayout.Tab?) {
               }

           })
        binding.newandhotviewpager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            binding.tablayout.selectTab(binding.tablayout.getTabAt(position))
            }
        })







        // Inflate the layout for this fragment
        return binding.root
    }


}