package com.example.nutriplan.feature.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.nutriplan.databinding.ActivityLoginBinding
import com.example.nutriplan.feature.mainmenu.MainMenu
import com.example.nutriplan.feature.signup.signup

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toRegisterBtn.setOnClickListener {
                val intent = Intent(this@login,signup::class.java)
                startActivity(intent)
                finish()
            }

            binding.loginBtn.setOnClickListener {
                val intent = Intent(this@login,MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
        supportActionBar?.hide()


    }
}