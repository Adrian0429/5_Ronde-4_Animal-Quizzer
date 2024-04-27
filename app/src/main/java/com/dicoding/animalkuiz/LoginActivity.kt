package com.dicoding.animalkuiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.animalkuiz.data.viewmodels.LoginViewModel
import com.dicoding.animalkuiz.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        // Bind UI components
        val inputUsername = findViewById<TextInputLayout>(R.id.inputUsername)
        val inputPassword = findViewById<TextInputLayout>(R.id.inputPass)
        val usernameEditText = inputUsername.editText as TextInputEditText
        val passwordEditText = inputPassword.editText as TextInputEditText
        val buttonGame = findViewById<MaterialButton>(R.id.buttonGame)

        // Observe the LiveData properties
        viewModel.loginResponse.observe(this) { response ->
            if (response != null) {
                if (response.success == true) {
                    val token = response.data?.token
                    val role = response.data?.role
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                    sharedPreferences.edit()
                        .putString("token", token)
                        .apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Login failed: ${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            buttonGame.isEnabled = !isLoading
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Handle button click to perform login
        buttonGame.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate inputs (optional)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
