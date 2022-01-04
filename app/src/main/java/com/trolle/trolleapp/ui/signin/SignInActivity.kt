package com.trolle.trolleapp.ui.signin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trolle.trolleapp.data.SignInBody
import com.trolle.trolleapp.data.SignInResponse
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.databinding.ActivitySignInBinding
import com.trolle.trolleapp.ui.home.HomeActivity
import com.trolle.trolleapp.ui.signup.SignUpActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            }
        }

        binding.textViewForgetPassword.setOnClickListener{
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }

        binding.textViewThenSignUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun signin(username: String, password: String){
        val retIn = RetrofitClient.apiInstance
        val signInInfo = SignInBody(username, password)
        retIn.login(signInInfo).enqueue(object : Callback<SignInResponse> {
            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Timeout", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                if (response.code() == 200) {
                    Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_LONG).show()

                    val sharedPreference: SharedPreference = SharedPreference(this@SignInActivity)
                    sharedPreference.save("id_user", response.body()!!.data.id)
                    sharedPreference.save("token", response.body()!!.data.token)

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                } else {
                    var responseMessage = response.message()
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        val msg = jsonObject.getString("message")
                        responseMessage = "$msg"
                    }
                    val errorMessage = "Error: $responseMessage"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
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