package com.example.nutriplan.feature.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityMainMenuBinding
import com.example.nutriplan.feature.dietplan.DietPlan
import com.example.nutriplan.feature.faq.FAQ
import com.example.nutriplan.feature.faq.FAQElement
import com.example.nutriplan.feature.faq.FAQuestionAdapter
import com.example.nutriplan.feature.makereccomendation.MakeReccomendation
import com.example.nutriplan.feature.profile.Profile
import com.example.nutriplan.feature.profile.ProfileViewModel
import com.example.nutriplan.model.Data1Item
import com.example.nutriplan.model.Plan

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
                            getProfile(token, id).observe(this@MainMenu) {
                                Log.e("ini","ini pesan setelah getProfile")
                                Log.e("Hasil",it.toString())
                                when (it) {
                                    is com.example.nutriplan.repository.Result.Loading -> {

                                    }
                                    is com.example.nutriplan.repository.Result.Success -> {
                                        Log.e("Data","Data Masuk")
                                        Log.e("data",it.data.data1[0].toString())
                                        insertCard(it.data.data1[0])
                                        Toast.makeText(
                                            this@MainMenu,
                                            "Success Get User Profile",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.e("Done","Data Selesai")

                                    }
                                    is com.example.nutriplan.repository.Result.Error -> {

                                        Toast.makeText(
                                            this@MainMenu,
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

        setContentView(binding.root)

        binding.apply {
            binding.menuProfile.setOnClickListener {
                val intent = Intent(this@MainMenu, Profile::class.java)
                startActivity(intent)
            }
            binding.MenuGenerate.setOnClickListener {
                val intent = Intent(this@MainMenu, MakeReccomendation::class.java)
                startActivity(intent)
            }
            binding.menuRecommendation.setOnClickListener {
                val intent = Intent(this@MainMenu, DietPlan::class.java)
                startActivity(intent)
            }
        }

    }

    private fun insertCard(data1Item: Data1Item) {
        binding.apply {
            usernamecardProfile.text = data1Item.name
            BMIProfile.text = data1Item.bmi.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_utama,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.FAQMenu->{
                val intent = Intent(this@MainMenu, FAQ::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

