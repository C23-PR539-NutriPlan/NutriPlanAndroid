package com.example.nutriplan.feature.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityMainMenuBinding
import com.example.nutriplan.feature.dietplan.DietPlan
import com.example.nutriplan.feature.faq.FAQ
import com.example.nutriplan.feature.form.Form
import com.example.nutriplan.feature.profile.Profile
import com.example.nutriplan.model.Data1Item

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private val viewModelFactory = ViewModelFactory.getInstance(this)
    private val mainMenuViewModel: MainMenuViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        mainMenuViewModel.apply {
            getToken().observe(this@MainMenu) { token ->
                if (token != null) {
                    getID().observe(this@MainMenu) { id ->
                        if (id != null) {
                            getProfile(id,token).observe(this@MainMenu) {
                                Log.e("ini", "ini pesan setelah getProfile")
                                Log.e("Hasil", it.toString())
                                when (it) {
                                    is com.example.nutriplan.repository.Result.Loading -> {
                                        showLoading(true)
                                    }
                                    is com.example.nutriplan.repository.Result.Success -> {
                                        Log.e("Data", "Data Masuk")
                                        Log.e("data", it.data.data1[0].toString())
                                        if (it.data.data1[0].gender == null) {
                                            val inten = Intent(this@MainMenu, Form::class.java)
                                            startActivity(inten)
                                            finish()

                                        } else {
                                            insertCard(it.data.data1[0])
                                            showLoading(false)
                                        }
                                    }
                                    is com.example.nutriplan.repository.Result.Error -> {

                                        Toast.makeText(
                                            this@MainMenu,
                                            "Failed Get User Profile",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        showLoading(false)
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }

        setContentView(binding.root)

        binding.apply {
            binding.menuProfile.setOnClickListener {
                val intent = Intent(this@MainMenu, Profile::class.java)
                startActivity(intent)
            }
            binding.menuFAQ.setOnClickListener {
                val intent = Intent(this@MainMenu, FAQ::class.java)
                startActivity(intent)
            }
            binding.MenuReccom.setOnClickListener {
                val intent = Intent(this@MainMenu, DietPlan::class.java)
                startActivity(intent)
            }
        }

    }

    private fun insertCard(data1Item: Data1Item) {
        binding.apply {
            usernamecardProfile.text = data1Item.name
            BMIProfile.text = data1Item.bmi.toString()
            if (data1Item.bmi < 19) {
                BmiStatus.text = "Underweight"
            } else if (data1Item.bmi >= 19 && data1Item.bmi < 25) {
                BmiStatus.text = "Normal"
            } else if (data1Item.bmi >= 25 && data1Item.bmi < 30) {
                BmiStatus.text = "Overweight"
            } else {
                BmiStatus.text = "Obese"
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu_utama, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.FAQMenu -> {
//                val intent = Intent(this@MainMenu, FAQ::class.java)
//                startActivity(intent)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressMenu.visibility = View.VISIBLE
        } else {
            binding.progressMenu.visibility = View.INVISIBLE
        }
    }


}

