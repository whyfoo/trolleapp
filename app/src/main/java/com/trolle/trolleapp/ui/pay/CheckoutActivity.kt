package com.trolle.trolleapp.ui.pay

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trolle.trolleapp.R
import com.trolle.trolleapp.databinding.ActivityCheckoutBinding
import com.trolle.trolleapp.ui.home.HomeSuccessActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = this.intent.extras
        val price = extras!!.getInt("price")
        val total = price+6500

        binding.textViewSubTotalPrice.text = getString(R.string.sub_total_price_dummy, price)
        binding.textViewGrandTotalPrice.text = getString(R.string.sub_total_price_dummy, total)

        binding.buttonPay.setOnClickListener {
            val intent = Intent(this, HomeSuccessActivity::class.java)
            intent.putExtra("totalPrice", total)
            startActivity(intent)
//            startActivity(Intent(this, HomeSuccessActivity::class.java))
            finish()
        }
    }
}