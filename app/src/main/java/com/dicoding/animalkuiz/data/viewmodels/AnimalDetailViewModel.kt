package com.dicoding.animalkuiz.data.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.animalkuiz.data.response.GetAnimalById
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalDetailViewModel(application: Application) : AndroidViewModel(application) {
    // LiveData to expose the details of a single animal
    private val _animalData = MutableLiveData<GetAnimalById?>()
    val animalData: LiveData<GetAnimalById?> = _animalData

    // Shared preferences for token
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        "MyAppPreferences",
        Context.MODE_PRIVATE
    )

    // Loading state LiveData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Error message LiveData
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // Retrieve the token from SharedPreferences
    private val token = sharedPreferences.getString("token", null)

    /**
     * Fetch the details of a single animal by its ID.
     * @param animalId The ID of the animal to fetch details for.
     */
    fun fetchAnimalDetails(animalId: String) {
        _isLoading.value = true

        // Use the API service to get animal details by ID
        val client = ApiConfig.getApiService().getAnimalById("Bearer $token", animalId)
        client.enqueue(object : Callback<GetAnimalById> {
            override fun onResponse(
                call: Call<GetAnimalById>,
                response: Response<GetAnimalById>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _animalData.value = response.body()
                } else {
                    _errorMessage.value = response.message()
                    Log.e("AnimalDetailViewModel", "onResponse failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetAnimalById>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message ?: "Unknown error"
                Log.e("AnimalDetailViewModel", "onFailure: ${t.message}")
            }
        })
    }
}
