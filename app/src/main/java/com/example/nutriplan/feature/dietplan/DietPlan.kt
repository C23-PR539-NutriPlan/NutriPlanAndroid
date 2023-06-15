package com.example.nutriplan.feature.dietplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityDietPlanBinding
import com.example.nutriplan.feature.dietplan.detail.DetailDietPlan
import com.example.nutriplan.feature.dietplan.post.PostDietPlan
import com.example.nutriplan.model.ListStoryItem
import com.example.nutriplan.model.Plan

class DietPlan : AppCompatActivity() {
    private lateinit var binding: ActivityDietPlanBinding
    private val viewModelFactory : ViewModelFactory = ViewModelFactory.getInstance(this)
    private val dietPlanViewModel : DietPlanViewModel by viewModels { viewModelFactory  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        showData()

        binding.apply {
            rvdietplan.layoutManager =layoutManager
            rvdietplan.addItemDecoration(itemDecoration)
        }
        getFromApi()

        binding.generateButton.setOnClickListener {
            val intent = Intent(this@DietPlan,PostDietPlan::class.java)
            startActivity(intent)
            finish()
        }
        supportActionBar?.hide()
    }

    private fun getFromApi(){
        dietPlanViewModel.getToken().observe(this){token->
            if (token!= null){
                dietPlanViewModel.getID().observe(this){id->
                    if (id != null){
                        dietPlanViewModel.getAllFood(id,token).observe(this){
                            when(it){
                                is com.example.nutriplan.repository.Result.Loading ->{
                                    showLoading(true)
                                }
                                is com.example.nutriplan.repository.Result.Success ->{
                                    Log.e("data",it.data.listStory.userAllergies.toString())
                                    Log.e("data",it.data.listStory.userPreferences.toString())
                                    Log.e("data",it.data.listStory.userCallories.toString())
                                    getListFoodRecomm(it.data.listStory.userRecommendation)
                                    showLoading(false)
                                }
                                is com.example.nutriplan.repository.Result.Error ->{
                                    Toast.makeText(this@DietPlan,"Failed to get food recomm",Toast.LENGTH_SHORT).show()
                                    showLoading(false)
                                }
                            }
                        }
                    }
                }
            }

        }
    }
    private fun getListFoodRecomm(data : List<ListStoryItem>){
        val adapter = DietPlanAdapter(data)
        adapter.setOnItemClick(object : DietPlanAdapter.OnitemClick{
            override fun onItemClicked(data: ListStoryItem) {
                val intent = Intent(this@DietPlan,DetailDietPlan::class.java)
                intent.putExtra(EXTRA_ID,data.id)
                startActivity(intent)
            }

        })

        binding.rvdietplan.adapter =adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressRecomm.visibility = View.VISIBLE
        } else {
            binding.progressRecomm.visibility = View.INVISIBLE
        }
    }

    private fun showData(){
        if (binding.rvdietplan.adapter?.itemCount == 0){
            binding.warning.visibility = View.VISIBLE

        }else if(binding.rvdietplan.size > 1){
            binding.warning.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }

}
