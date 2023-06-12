package com.example.nutriplan.feature.dietplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityDietPlanBinding
import com.example.nutriplan.feature.dietplan.detail.DetailDietPlan
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


        binding.apply {
            rvdietplan.layoutManager =layoutManager
            rvdietplan.addItemDecoration(itemDecoration)
        }
        getFromApi()

        supportActionBar?.hide()
    }

    private fun getFromApi(){
        dietPlanViewModel.getID().observe(this){id->
            if (id != null){
                dietPlanViewModel.getAllFood(id).observe(this){
                    when(it){
                        is com.example.nutriplan.repository.Result.Loading ->{

                        }
                        is com.example.nutriplan.repository.Result.Success ->{
                            Log.e("data",it.data.listStory.toString())
                            getListFoodRecomm(it.data.listStory)
                            Toast.makeText(this@DietPlan,"Succes to get food recomm",Toast.LENGTH_SHORT).show()
                        }
                        is com.example.nutriplan.repository.Result.Error ->{
                            Toast.makeText(this@DietPlan,"Failed to get food recomm",Toast.LENGTH_SHORT).show()
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

    companion object {
        const val EXTRA_ID = "extra_id"
    }

}