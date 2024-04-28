package com.dicoding.animalkuiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.animalkuiz.data.response.DataItemQuiz
import com.dicoding.animalkuiz.data.viewmodels.QuestViewModel
import com.dicoding.animalkuiz.data.viewmodels.ScanViewModel
import com.dicoding.animalkuiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var viewModel: ScanViewModel
    private var answer: String? = null
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ScanViewModel::class.java]

        val data = arguments?.getString("hai")

        viewModel.scanResponse.observe(viewLifecycleOwner) { response ->
            if (response != null && response.data != null) {
//                Log.d("Info data vron : ", response.data.toString())
                binding.apply {
                    response.data[count].let { res ->
                        answer = res?.correctAnswer
                        tvQuestion.text = res?.question
                        if (res != null && !res.wrongAnswers.isNullOrEmpty()) {
                            val scrambled = listOf(
                                res.correctAnswer,
                                res.wrongAnswers[0],
                                res.wrongAnswers[1],
                                res.wrongAnswers[2]
                            )
                            scrambled.shuffled()

                            rb1.text =  scrambled[0]
                            rb2.text = scrambled[1]
                            rb3.text = scrambled[2]
                            rb4.text = scrambled[3]
                        }
                    }
                }

            } else {
                binding.tvQuestion.text = "No response"
            }
        }

        binding.submitBtn.setOnClickListener {
            val checkedRadioButtonId = binding.rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                val selectedRadioButton = view.findViewById<RadioButton>(checkedRadioButtonId)
                val selectedAnswer = selectedRadioButton.text.toString()

                val isCorrect = selectedAnswer == answer

                if (isCorrect) {
                    Toast.makeText(requireContext(), "Selamat, Anda benar!", Toast.LENGTH_SHORT).show()
                    if(count < 2) {
                        count++
                        viewModel.getQuizQuestions(data.toString())
                    } else {
                        val bundle = Bundle()
                        bundle.putString("hai", data.toString())
                        val nav = findNavController()
                        nav.navigate(R.id.ResultFragment, bundle)
                    }
                } else {
                    Toast.makeText(requireContext(), "Maaf, Anda salah!", Toast.LENGTH_SHORT).show()
                }

                binding.rgOptions.clearCheck()
            }
        }

        viewModel.getQuizQuestions(data.toString())
    }
}