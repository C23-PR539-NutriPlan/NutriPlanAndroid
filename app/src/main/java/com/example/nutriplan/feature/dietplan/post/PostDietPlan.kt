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

class PostDietPlan : AppCompatActivity() {
    private lateinit var binding: ActivityPostDietPlanBinding
    private val viewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val postDietPlanViewModel: PostDietPlanViewModel by viewModels { viewModelFactory }
    private var x: List<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        post()

    }

    private fun post() {
        postDietPlanViewModel.getID().observe(this) { id ->
            if (id != null) {
                postDietPlanViewModel.getAllFood(id).observe(this) {
                    when (it) {
                        is com.example.nutriplan.repository.Result.Loading -> {
                            showLoading(true)
                        }
                        is com.example.nutriplan.repository.Result.Success -> {
                            Log.e("data alergi", it.data.listStory.userAllergies.toString())
                            val favorit = it.data.listStory.userPreferences
                            val allergi = it.data.listStory.userAllergies

                            postDietPlanViewModel.postForRecomm(id, 500, allergi, favorit)
                                .observe(this) {
                                    when (it) {
                                        is com.example.nutriplan.repository.Result.Loading -> {

                                        }
                                        is com.example.nutriplan.repository.Result.Success -> {

                                        }
                                        is com.example.nutriplan.repository.Result.Error -> {
                                            Log.e("user allergies saat post", allergi.toString())
                                            Log.e("user favorites saat post", favorit.toString())
                                        }
                                    }
                                }
                            showLoading(false)
                            Toast.makeText(
                                this@PostDietPlan,
                                "Succes to get food recomm",
                                Toast.LENGTH_SHORT
                            ).show()
                            back()
                        }
                        is com.example.nutriplan.repository.Result.Error -> {
                            showLoading(false)
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarPost.visibility = View.VISIBLE
        } else {
            binding.progressBarPost.visibility = View.INVISIBLE
        }
    }

    private fun back() {
        val intent = Intent(this@PostDietPlan, DietPlan::class.java)
        startActivity(intent)
        finish()
    }
}
