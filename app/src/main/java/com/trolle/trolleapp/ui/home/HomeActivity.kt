package com.trolle.trolleapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trolle.trolleapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.menglogo)
        }
    }
}