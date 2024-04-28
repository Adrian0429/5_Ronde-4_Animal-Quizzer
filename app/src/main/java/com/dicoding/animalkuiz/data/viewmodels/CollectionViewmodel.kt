package com.dicoding.animalkuiz.data.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.animalkuiz.data.response.GetInventory
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionViewmodel(application: Application) : AndroidViewModel(application) {

    // LiveData to expose the list of animals' inventory
    private val _inventoryData = MutableLiveData<GetInventory?>()
    val inventoryData: LiveData<GetInventory?> = _inventoryData

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        "MyAppPreferences",
        Context.MODE_PRIVATE
    )

    // LiveData to track loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData to track error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    val token = sharedPreferences.getString("token", null)

    fun fetchAllAnimalsInventory() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getAllAnimalsInventory("Bearer $token")
        client.enqueue(object : Callback<GetInventory> {
            override fun onResponse(
                call: Call<GetInventory>,
                response: Response<GetInventory>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _inventoryData.value = response.body()
                } else {

                    _errorMessage.value = response.message()
                    Log.e("AllCollectionViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetInventory>, t: Throwable) {

                _isLoading.value = false


                _errorMessage.value = t.message ?: "Unknown error"
                Log.e("AllCollectionViewModel", "onFailure: ${t.message}")
            }
        })
    }
}
