package com.example.nutriplan.feature.signup

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nutriplan.ViewModelFactory
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
                onClicktoLogin()
            }
            signupBtn.setOnClickListener {
                onClickButtonRegister()
            }
        }


        supportActionBar?.hide()

    }

    private fun onClickButtonRegister(){
        val viewModel : ViewModelFactory = ViewModelFactory.getInstance(this)
        val signUpViewModel : SignUpViewModel by viewModels{viewModel}
        val nameValue = binding.signupName.text.toString().trim()
        val emailValue = binding.signupEmail.text.toString().trim()
        val passwordValue = binding.signupPassword.text.toString().trim()
        if(nameValue.isEmpty() || emailValue.isEmpty() || passwordValue.length < 6 ){
            Toast.makeText(this@signup,"Sign Up Failed",Toast.LENGTH_SHORT).show()
        }else{
            signUpViewModel.saveUserRegister(nameValue,emailValue,passwordValue).observe(this@signup){
                when(it){
                    is com.example.nutriplan.repository.Result.Loading ->{
                        showLoading(true)
                    }
                    is com.example.nutriplan.repository.Result.Success -> {
                        Toast.makeText(this@signup,"Sign Up Succes",Toast.LENGTH_SHORT).show()
                        showLoading(false)
                        onClicktoLogin()
                    }
                    is com.example.nutriplan.repository.Result.Error -> {
                        Toast.makeText(this@signup,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun onClicktoLogin(){
        val intent = Intent(this@signup,login::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(isLoading : Boolean){
        if (isLoading){
            binding.progressRegister.visibility = View.VISIBLE
        }else{
            binding.progressRegister.visibility = View.INVISIBLE
        }
    }

    private fun showAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -75f, 75f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}
