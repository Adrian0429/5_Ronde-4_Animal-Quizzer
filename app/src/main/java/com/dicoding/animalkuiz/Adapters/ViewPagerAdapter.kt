package com.dicoding.animalkuiz.Adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.animalkuiz.AllCollectionFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // Define your tabs/fragments here
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllCollectionFragment() // Your fragment for the "Semua" tab
            1 -> AllCollectionFragment() // Your fragment for the "Reptil" tab
            2 -> AllCollectionFragment() // Your fragment for the "Aves" tab
            3 -> AllCollectionFragment() // Your fragment for the "Pisces" tab
            4 -> AllCollectionFragment() // Your fragment for the "Mamalia" tab
            else -> throw IllegalArgumentException("Unexpected position: $position")
        }
    }

    override fun getItemCount(): Int {
        // Return the number of tabs
        return 5
    }
}
