package com.dicoding.animalkuiz.data.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.animalkuiz.data.response.GetQuiz
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanViewModel(application: Application): AndroidViewModel(application) {
    private val _scanResponse = MutableLiveData<GetQuiz>()
    val scanResponse: LiveData<GetQuiz> = _scanResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        "MyAppPreferences",
        Context.MODE_PRIVATE
    )

    fun getQuizQuestions(id: String) {
        _isLoading.value = true

        val token = sharedPreferences.getString("token", null)

        val client = ApiConfig.getApiService().getQuiz(id, "Bearer $token")
        client.enqueue(object : Callback<GetQuiz> {
            override fun onResponse(call: Call<GetQuiz>, response: Response<GetQuiz>) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _scanResponse.value = response.body()
                    _responseMessage.value = response.body()?.message.toString()
                } else {
                    _responseMessage.value = response.body()?.message.toString()
                }
            }

            override fun onFailure(call: Call<GetQuiz>, t: Throwable) {
                _isLoading.value = false
                _responseMessage.value = t.message.toString()
            }
        })
    }
}