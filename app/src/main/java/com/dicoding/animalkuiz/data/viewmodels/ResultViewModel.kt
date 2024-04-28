package com.dicoding.animalkuiz.data.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.animalkuiz.data.response.GetAnimalById
import com.dicoding.animalkuiz.data.response.ResultResponse
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel(application: Application): AndroidViewModel(application) {
    private val _resultResponse = MutableLiveData<GetAnimalById>()
    val resultResponse: LiveData<GetAnimalById> = _resultResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        "MyAppPreferences",
        Context.MODE_PRIVATE
    )

    fun getDetailAnimal(id: String) {
        _isLoading.value = true

        val token = sharedPreferences.getString("token", null)

        val client = ApiConfig.getApiService().getAnimalById(id, "Bearer $token")
        client.enqueue(object : Callback<GetAnimalById> {
            override fun onResponse(call: Call<GetAnimalById>, response: Response<GetAnimalById>) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _resultResponse.value = response.body()
                    _responseMessage.value = response.body()?.message.toString()
                } else {
                    _responseMessage.value = response.body()?.message.toString()
                }
            }

            override fun onFailure(call: Call<GetAnimalById>, t: Throwable) {
                _isLoading.value = false
                _responseMessage.value = t.message.toString()
            }

        })
    }
}