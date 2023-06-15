package com.example.nutriplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.nutriplan.feature.login.login

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val logo = findViewById<ImageView>(R.id.image)
        logo.alpha = 0f
        logo.animate().setDuration(2000).alpha(1f).withEndAction{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
