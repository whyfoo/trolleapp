package com.trolle.trolleapp.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.trolle.trolleapp.data.SignInBody
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.databinding.ActivitySignInBinding
import com.trolle.trolleapp.ui.home.HomeActivity
import com.trolle.trolleapp.ui.signup.SignUpActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private var backPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener {
            val username: String = binding.editTextUsername.getText().toString()
            val password: String = binding.editTextPassword.getText().toString()

            if (username.equals("")) {
                binding.textFieldUsername.setError("Name cannot be empty.")
            } else if (password.equals("")) {
                binding.textFieldPassword.setError("Password cannot be empty.")
            } else {
                signin(username, password)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        binding.textViewThenSignUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun signin(username: String, password: String){
        val retIn = RetrofitClient.apiInstance
        val signInInfo = SignInBody(username, password)
        retIn.login(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    Toast.makeText(applicationContext, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onBackPressed() {
        if (backPressed) {
            super.onBackPressed()
            return
        }
        this.backPressed = true
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            this.backPressed = false
        }, 2000)
    }
}