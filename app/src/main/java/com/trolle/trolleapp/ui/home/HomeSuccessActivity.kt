package com.trolle.trolleapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.trolle.trolleapp.R
import com.trolle.trolleapp.data.DisconnectRaspi
import com.trolle.trolleapp.data.UserRaspi
import com.trolle.trolleapp.data.UserRaspiResponse
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.databinding.ActivityHomeSuccessBinding
import com.trolle.trolleapp.ui.pay.CheckoutActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = this.intent.extras
        val totalPrice = extras!!.getInt("totalPrice")

        val sharedPreference: SharedPreference = SharedPreference(this)
        val idUser = sharedPreference.getValueInt("id_user")

        binding.textViewGrandTotalPrice.text = getString(R.string.sub_total_price_dummy, totalPrice)

        binding.buttonBackToHome.setOnClickListener {
            disconnectRaspi(idUser)
            finish()
        }
    }

    private fun disconnectRaspi(id_user: Int){
        val retIn = RetrofitClient.apiInstance
        val idUser = DisconnectRaspi(id_user)
        retIn.disconnectRaspi(idUser).enqueue(object : Callback<UserRaspiResponse> {
            override fun onFailure(call: Call<UserRaspiResponse>, t: Throwable) {
                Toast.makeText(this@HomeSuccessActivity, "Timeout", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserRaspiResponse>, response: Response<UserRaspiResponse>) {
                if (response.code() == 200) {
                    Toast.makeText(this@HomeSuccessActivity, response.body()!!.message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@HomeSuccessActivity, HomeActivity::class.java))
                } else {
                    var responseMessage = response.message()
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        val msg = jsonObject.getString("message")
                        responseMessage = "$msg"
                    }
                    val errorMessage = "Error: $responseMessage"
                    Toast.makeText(this@HomeSuccessActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}