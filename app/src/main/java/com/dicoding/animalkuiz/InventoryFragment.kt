package com.dicoding.animalkuiz
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.animalkuiz.Adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class InventoryFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        // Initialize ViewPager and TabLayout
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tablayout)

        // Set up ViewPagerAdapter
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter

        // Set up TabLayoutMediator to link ViewPager and TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set the title of each tab
            tab.text = when (position) {
                0 -> "Semua"
                1 -> "Reptil"
                2 -> "Aves"
                3 -> "Pisces"
                4 -> "Mamalia"
                else -> throw IllegalArgumentException("Unexpected position: $position")
            }
        }.attach()

        return view
    }
}
