package com.dicoding.animalkuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.animalkuiz.Adapters.AdapterInventory
import com.dicoding.animalkuiz.data.viewmodels.CollectionViewmodel

class AllCollectionFragment : Fragment(), AdapterInventory.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterInventory: AdapterInventory
    private val viewModel: CollectionViewmodel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_collection, container, false)
        recyclerView = view.findViewById(R.id.rv_deck)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        adapterInventory = AdapterInventory(this)
        recyclerView.adapter = adapterInventory

        viewModel.inventoryData.observe(viewLifecycleOwner) { inventory ->
            inventory?.data?.let { data ->
                adapterInventory.submitList(data)
            }
        }

        viewModel.fetchAllAnimalsInventory()

        return view
    }

//    override fun onItemClick(position: Int) {
//        // Get the data item at the clicked position
//        val clickedItem = adapterInventory.currentList[position]
//        val animalId = clickedItem.id
//
//        if (animalId == null) {
//            // Handle the null case, e.g., return early or show an error message
//            return
//        }
//        val detailFragment = AnimalDetailFragment.newInstance(animalId)
//
//        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//
//        // Replace the current fragment with AnimalDetailFragment
//        fragmentTransaction.replace(R.id.nav_host_fragment_container, detailFragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }

}
