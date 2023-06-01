package com.example.nutriplan.feature.makereccomendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityMakeReccomendationBinding
import com.example.nutriplan.feature.mainmenu.MainMenu

class MakeReccomendation : AppCompatActivity() {
    private lateinit var binding: ActivityMakeReccomendationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeReccomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            binding.finishrecomm.setOnClickListener {
                val intent = Intent(this@MakeReccomendation,MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}