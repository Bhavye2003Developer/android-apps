package com.example.capsule.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.capsule.utility.Utils

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).

        // an array of fragments.
        val fragments = Utils.fragments
        return fragments[position]
    }

    companion object {
        private const val ARG_OBJECT = "object"
    }
}