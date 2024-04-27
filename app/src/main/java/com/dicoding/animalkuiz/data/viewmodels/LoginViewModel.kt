package com.dicoding.animalkuiz.data.viewmodels
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.animalkuiz.data.response.LoginRequest
import com.dicoding.animalkuiz.data.response.LoginResponse
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(username: String, password: String) {
        _isLoading.value = true
        val request = LoginRequest(username, password)
        val client = ApiConfig.getApiService().login(request)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    _errorMessage.value = response.message()
                    Log.e("LoginViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message ?: "Unknown error"
                Log.e("LoginViewModel", "onFailure: ${t.message}")
            }
        })
    }

}
