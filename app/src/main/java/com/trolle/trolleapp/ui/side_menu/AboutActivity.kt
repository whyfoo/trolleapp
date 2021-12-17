package com.trolle.trolleapp.ui.side_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trolle.trolleapp.R
import android.content.Intent
import android.net.Uri
import android.view.View


class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    fun openBrowser(view: View) {

        //Get url from tag
        val url = view.getTag() as String
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)

        //pass the url to intent data
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}