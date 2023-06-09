package com.example.nutriplan.feature.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityProfileBinding
import com.example.nutriplan.feature.login.login

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModelFactory = ViewModelFactory.getInstance(this)
    private  val profileViewModel : ProfileViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.apply {
            logoutBtn.setOnClickListener {
                profileViewModel.clearToken()
                backToLogin()
            }
        }
    }

    private fun backToLogin(){
        val intent = Intent(this@Profile,login::class.java)
        startActivity(intent)
        finish()
    }

    private fun dataProfile(){
        viewModelFactory.apply {

        }
    }
}