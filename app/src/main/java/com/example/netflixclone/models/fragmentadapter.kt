package com.example.netflixclone.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.netflixclone.fragments.Action
import com.example.netflixclone.fragments.Comedy
import com.example.netflixclone.fragments.Drama

class fragmentadapter(fm:FragmentManager,
    lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            Action()
        else if (position == 1)
            Comedy()
        else
            Drama()

    }
}