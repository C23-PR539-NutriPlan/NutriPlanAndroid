package com.example.nutriplan.feature.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityProfileBinding
import com.example.nutriplan.feature.login.login
import com.example.nutriplan.model.Data1Item

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModelFactory = ViewModelFactory.getInstance(this)
    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)


        supportActionBar?.hide()
        profileViewModel.apply {
            getToken().observe(this@Profile) { token ->
                if (token != null) {
                    getID().observe(this@Profile) { id ->
                        if (id != null) {
                            getProfile(token, id).observe(this@Profile) {
                                Log.e("ini","ini pesan setelah getProfile")
                                Log.e("Hasil",it.toString())
                                when (it) {
                                    is com.example.nutriplan.repository.Result.Loading -> {
                                        showLoading(true)
                                    }
                                    is com.example.nutriplan.repository.Result.Success -> {
                                        Log.e("Data","Data Masuk")
                                        Log.e("data",it.data.data1[0].toString())
                                        insertProfile(it.data.data1[0])
                                        showLoading(false)
                                        Toast.makeText(
                                            this@Profile,
                                            "Success Get User Profile",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.e("Done","Data Selesai")

                                    }
                                    is com.example.nutriplan.repository.Result.Error -> {
                                        showLoading(false)
                                        Toast.makeText(
                                            this@Profile,
                                            "Failed Get User Profile",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        binding.apply {
            logoutBtn.setOnClickListener {
                profileViewModel.clearToken()
                backToLogin()
            }
        }
        setContentView(binding.root)
    }

    private fun backToLogin() {
        val intent = Intent(this@Profile, login::class.java)
        startActivity(intent)
        finish()
    }

    private fun insertProfile(data1: Data1Item) {
        binding.apply {
            usernameSetting.text = data1.name
            Log.e("ini nama user", usernameSetting.text.toString())
            heightSetting.text = data1.height.toString()
            Log.e("ini tinggi user", heightSetting.text.toString())
            weightSetting.text = data1.weight.toString()
            Log.e("ini berat user", weightSetting.text.toString())
            GenderSetting.text = data1.gender
            Log.e("ini gender user", GenderSetting.text.toString())
            AgeSetting.text = data1.age.toString()
            Log.e("ini age user", AgeSetting.text.toString())
            BMISetting.text = data1.bmi.toString()
            Log.e("ini BMI user", BMISetting.text.toString())

            Log.e("ini nama user", usernameSetting.text.toString())
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressProfile.visibility = View.VISIBLE
        } else {
            binding.progressProfile.visibility = View.INVISIBLE
        }
    }

}
