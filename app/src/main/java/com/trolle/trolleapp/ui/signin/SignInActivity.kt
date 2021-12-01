package com.trolle.trolleapp.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.trolle.trolleapp.R
import com.trolle.trolleapp.ui.home.HomeActivity
import com.trolle.trolleapp.ui.main.MainActivity
import com.trolle.trolleapp.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private var backPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signinButton = findViewById<Button>(R.id.button_sign_in)
        signinButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        val signupButton = findViewById<TextView>(R.id.text_view_then_sign_up)
        signupButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
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