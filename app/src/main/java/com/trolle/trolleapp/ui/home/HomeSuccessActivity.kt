package com.trolle.trolleapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trolle.trolleapp.R
import com.trolle.trolleapp.databinding.ActivityHomeSuccessBinding
import com.trolle.trolleapp.ui.pay.CheckoutActivity

class HomeSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = this.intent.extras
        val totalPrice = extras!!.getInt("totalPrice")

        binding.textViewGrandTotalPrice.text = getString(R.string.sub_total_price_dummy, totalPrice)

        binding.buttonBackToHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}