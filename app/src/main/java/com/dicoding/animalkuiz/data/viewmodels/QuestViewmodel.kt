package com.dicoding.animalkuiz.data.viewmodels
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.animalkuiz.data.response.MintaquestResponse
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestViewModel(application: Application) : AndroidViewModel(application) {

    private val _questData = MutableLiveData<MintaquestResponse?>()
    val questData: LiveData<MintaquestResponse?> = _questData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        "MyAppPreferences",
        Context.MODE_PRIVATE
    )

    fun fetchQuestForUserByAnimalId(animalTypeId: String) {
        _isLoading.value = true

        // Retrieve the token from SharedPreferences
        val token = sharedPreferences.getString("token", null)

        // Perform the API request with the token in the header
        val client = ApiConfig.getApiService().getQuestForUserByAnimalId(animalTypeId, "Bearer $token")
        client.enqueue(object : Callback<MintaquestResponse> {
            override fun onResponse(
                call: Call<MintaquestResponse>,
                response: Response<MintaquestResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _questData.value = response.body()
                } else {
                    Log.e("QuestViewModel", "onFailure: ${response.message()}")
                    _questData.value = null
                }
            }

            override fun onFailure(call: Call<MintaquestResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("QuestViewModel", "onFailure: ${t.message}")
                _questData.value = null
            }
        })
    }
}
