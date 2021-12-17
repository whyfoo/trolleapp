package com.trolle.trolleapp.ui.side_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trolle.trolleapp.R

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Help")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}