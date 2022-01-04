package com.trolle.trolleapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trolle.trolleapp.R
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.databinding.ActivityHomeBinding
import com.trolle.trolleapp.ui.side_menu.AboutActivity
import com.trolle.trolleapp.ui.side_menu.EditProfileActivity
import com.trolle.trolleapp.ui.side_menu.HelpActivity
import com.trolle.trolleapp.ui.signin.SignInActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var backPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener{
            binding.drawerLayout.open()
        }

        val sharedPreference: SharedPreference = SharedPreference(this)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            when(menuItem.itemId){
                R.id.item_edit_profile ->
                    startActivity(Intent(this, EditProfileActivity::class.java))
                R.id.item_see_shopping_history ->
                    Toast.makeText(this, "shopping history", Toast.LENGTH_SHORT).show()
                R.id.item_about ->
                    startActivity(Intent(this, AboutActivity::class.java))
                R.id.item_help ->
                    startActivity(Intent(this, HelpActivity::class.java))
                R.id.item_sign_out -> {
                    if (sharedPreference.getValueBoolien("status", false).equals(false)){
                        sharedPreference.clearSharedPreference()
                        startActivity(Intent(this, SignInActivity::class.java))
                        Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Please checkout your order first!", Toast.LENGTH_LONG).show()
                    }

                }

            }
            menuItem.isChecked = true
            binding.drawerLayout.close()
            true
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)


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