package com.dicoding.animalkuiz.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.animalkuiz.data.response.DataItemQuiz

class QuestionViewModel: ViewModel() {
    private val _questionData = MutableLiveData<List<DataItemQuiz>>()
    val questionData: LiveData<List<DataItemQuiz>> = _questionData

}