package com.dicoding.animalkuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.animalkuiz.data.viewmodels.AnimalDetailViewModel

class AnimalDetailFragment : Fragment() {

    private val viewModel: AnimalDetailViewModel by viewModels()
    private var animalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the animal ID passed in arguments
        arguments?.let {
            animalId = it.getString(ARG_ANIMAL_ID)
        }
        // Fetch animal details using the ViewModel
        animalId?.let {
//            viewModel.fetchAnimalDetails(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_animal_detail, container, false)

        // Observe animal details and display them in the view
        viewModel.animalData.observe(viewLifecycleOwner) { animal ->
            animal?.let {
                // Update the view components with animal details
                // For example:
                // val animalNameTextView: TextView = view.findViewById(R.id.animal_name)
                // animalNameTextView.text = animal.name
                // Add code here to update other UI components with animal details
            }
        }

        return view
    }

    companion object {
        private const val ARG_ANIMAL_ID = "animal_id"

        /**
         * Factory method to create a new instance of AnimalDetailFragment
         * with the given animal ID.
         */
        fun newInstance(animalId: String): AnimalDetailFragment {
            val fragment = AnimalDetailFragment()
            val args = Bundle().apply {
                putString(ARG_ANIMAL_ID, animalId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
