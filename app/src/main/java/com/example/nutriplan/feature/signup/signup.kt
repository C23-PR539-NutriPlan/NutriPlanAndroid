package com.example.nutriplan.feature.signup

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriplan.databinding.ActivitySignupBinding
import com.example.nutriplan.feature.login.login

class signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toLoginBtn.setOnClickListener {
                val intent = Intent(this@signup,login::class.java)
                startActivity(intent)
                finish()
            }
        }


        supportActionBar?.hide()

    }
}