package com.example.nutriplan.feature.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriplan.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
    }
}