package com.dicoding.animalkuiz

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.animalkuiz.data.viewmodels.ResultViewModel
import com.dicoding.animalkuiz.data.viewmodels.ScanViewModel
import com.dicoding.animalkuiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ResultViewModel::class.java]

        val data = arguments?.getString("hai")

        viewModel.resultResponse.observe(viewLifecycleOwner) { response ->
            binding.apply {
                val res = response.data
                imageView2.setImageURI(Uri.parse("https://tf4zs3dh-8080.asse.devtunnels.ms/api/v1/files/${res?.realImage}"))
                tvAnimalName.text = response.data?.name
                tvDesc.text = response.data?.description
            }
        }

        binding.button.setOnClickListener {
            val nav = findNavController()
            nav.navigate(R.id.InventoryFragment)
        }
    }
}