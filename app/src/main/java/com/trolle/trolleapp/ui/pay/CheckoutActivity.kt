package com.trolle.trolleapp.ui.pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trolle.trolleapp.R
import com.trolle.trolleapp.databinding.ActivityCheckoutBinding
import com.trolle.trolleapp.ui.home.HomeSuccessActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPay.setOnClickListener {
            startActivity(Intent(this, HomeSuccessActivity::class.java))
            finish()
        }
    }
}