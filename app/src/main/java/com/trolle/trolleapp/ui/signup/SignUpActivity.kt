package com.trolle.trolleapp.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trolle.trolleapp.data.User
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.databinding.ActivitySignUpBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern.compile

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener{
            val username: String = binding.editTextUsername.getText().toString()
            val email: String = binding.editTextEmail.getText().toString()
            val password: String = binding.editTextPassword.getText().toString()
            val password2: String = binding.editTextRetypePassword.getText().toString()

            val emailRegex = compile("^[\\w&*_~]+(\\.?[\\w&*_~]+)*@[^-][\\w\\-\\.]+$")
            val passwordRegex = compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}")

            if (username.equals("")) {
                binding.textFieldUsername.setError("Name cannot be empty.")
            } else if (!emailRegex.matcher(email).matches()) {
                binding.textFieldEmail.setError("Email format incorrect")
            } else if (!password.equals(password2)){
                binding.textFieldRetypePassword.setError("Password does not match")
            } else if (!passwordRegex.matcher(password).matches()) {
                binding.textFieldPassword.setError("Your password must contain minimal 6 characters, including minimal 1 UpperCase and 1 Number")
            } else {
                signUp(username, email, password)
                Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.textViewThenSignIn.setOnClickListener{
            finish()
        }
    }

    private fun signUp(username: String, email: String, password: String){

        val retIn = RetrofitClient.apiInstance
        val registerInfo = User(username, email, password)

        retIn.registerUser(registerInfo).enqueue(object :
                Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(applicationContext, "Registration success!", Toast.LENGTH_SHORT)
                            .show()

                } else {
                    Toast.makeText(applicationContext, "Registration failed!", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

}