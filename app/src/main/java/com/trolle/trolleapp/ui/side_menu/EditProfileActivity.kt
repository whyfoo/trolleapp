package com.trolle.trolleapp.ui.side_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.trolle.trolleapp.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Edit Profile")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}