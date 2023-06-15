package com.example.nutriplan.feature.dietplan.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityPostDietPlanBinding
import com.example.nutriplan.feature.dietplan.DietPlan
import com.example.nutriplan.feature.dietplan.detail.DetailPlanViewModel
import com.example.nutriplan.feature.login.login
import com.example.nutriplan.feature.mainmenu.MainMenu

class PostDietPlan : AppCompatActivity() {
    private lateinit var binding: ActivityPostDietPlanBinding
    private val viewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val postDietPlanViewModel: PostDietPlanViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        post()

    }

    private fun post() {
        postDietPlanViewModel.getToken().observe(this) { token ->
            if (token != null) {
                postDietPlanViewModel.getID().observe(this) { id ->
                    if (id != null) {
                        postDietPlanViewModel.getAllFood(id, token).observe(this) {
                            when (it) {
                                is com.example.nutriplan.repository.Result.Loading -> {

                                }
                                is com.example.nutriplan.repository.Result.Success -> {
                                    Log.e("data alergi", it.data.listStory.userAllergies.toString())
                                    val kalori = it.data.listStory.userCallories
                                    val favorit = it.data.listStory.userPreferences
                                    val allergi = it.data.listStory.userAllergies
                                    postDietPlanViewModel.postForRecomm(
                                        id,
                                        kalori,
                                        allergi,
                                        favorit
                                    )
                                        .observe(this) {
                                            when (it) {
                                                is com.example.nutriplan.repository.Result.Loading -> {

                                                }
                                                is com.example.nutriplan.repository.Result.Success -> {

                                                }
                                                is com.example.nutriplan.repository.Result.Error -> {
                                                    Log.e(
                                                        "user allergies saat post",
                                                        allergi.toString()
                                                    )
                                                    Log.e(
                                                        "user favorites saat post",
                                                        favorit.toString()
                                                    )
                                                }
                                            }
                                        }

                                    back()


                                }
                                is com.example.nutriplan.repository.Result.Error -> {

                                    Toast.makeText(
                                        this@PostDietPlan,
                                        "Failed to get food recomm",
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


    private fun back() {
        val x = binding.progressBarPost
        x.alpha = 0f
        x.animate().setDuration(30000).alpha(1f).withEndAction{
            val intent = Intent(this@PostDietPlan, DietPlan::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(
                this@PostDietPlan,
                "Succes to get food recomm",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
