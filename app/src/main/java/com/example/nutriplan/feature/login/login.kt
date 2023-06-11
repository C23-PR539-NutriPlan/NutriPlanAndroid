package com.example.nutriplan.feature.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nutriplan.ViewModelFactory

import com.example.nutriplan.databinding.ActivityLoginBinding
import com.example.nutriplan.feature.form.Form
import com.example.nutriplan.feature.mainmenu.MainMenu
import com.example.nutriplan.feature.signup.signup

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactories: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModels: LoginViewModel by viewModels { viewModelFactories }
        loginViewModels.apply {
            getToken().observe(this@login) {
                if (it != null) {
                    Toast.makeText(this@login, "Login Success", Toast.LENGTH_SHORT).show()
                    onClicktoMainMenu()
                }
            }
        }

        binding.apply {
            toRegisterBtn.setOnClickListener {
                onClicktoSignUp()
            }
            binding.loginBtn.setOnClickListener {
                onClickButtonLogin()
            }
        }
        supportActionBar?.hide()
    }

    private fun onClickButtonLogin() {
        val viewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

        val emailValue = binding.loginEmail.text.toString().trim()
        val passwordValue = binding.loginPassword.text.toString().trim()
        if (passwordValue.length < 6) {
            Toast.makeText(this@login, "Login Failed", Toast.LENGTH_SHORT).show()
        } else {
            loginViewModel.getUserLogin(emailValue, passwordValue).observe(this@login) {
                when (it) {
                    is com.example.nutriplan.repository.Result.Loading -> {
                        showLoading(true)
                    }
                    is com.example.nutriplan.repository.Result.Success -> {
                        Toast.makeText(this@login, "Login Success", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                        onClicktoMainMenu()
                    }
                    is com.example.nutriplan.repository.Result.Error -> {
                        Toast.makeText(this@login, "Login Failed", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun onClicktoSignUp() {
        val intent = Intent(this@login, signup::class.java)
        startActivity(intent)
        finish()
    }

    private fun onClicktoMainMenu() {
        val viewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels { viewModelFactory }
        loginViewModel.getState().observe(this) {
            if (it == null) {
                val intent = Intent(this@login, Form::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@login, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressLogin.visibility = View.VISIBLE
        } else {
            binding.progressLogin.visibility = View.INVISIBLE
        }
    }
}