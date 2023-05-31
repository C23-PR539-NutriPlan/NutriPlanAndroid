package com.example.nutriplan.feature.dietplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityDietPlanBinding
import com.example.nutriplan.feature.mainmenu.MainMenuAdapter
import com.example.nutriplan.model.Plan

class DietPlan : AppCompatActivity() {
    val listPlan  = ArrayList<Plan>()
    private lateinit var binding: ActivityDietPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            binding.rvdietplan.setHasFixedSize(true)
            if (binding.rvdietplan.size == 0){
                listPlan.addAll(getlistPlan())
            }
        }
        showListPlan()
        supportActionBar?.hide()
    }

    private fun getlistPlan():ArrayList<Plan>{
        val dataDay = resources.getStringArray(R.array.Day)
        val dataMax_Cal = resources.getStringArray(R.array.Max_Cal)
        val dataFood = resources.getStringArray(R.array.Food)
        val listPLAN = ArrayList<Plan>()

        for(i in dataDay.indices){
            val PLAN = Plan(dataDay[i],dataFood[i],dataMax_Cal[i])
            listPLAN.add(PLAN)
        }
        return listPLAN
    }

    private fun showListPlan(){
        for (i in  0 .. 9){
            binding.rvdietplan.layoutManager = LinearLayoutManager(this)
            val list = DietPlanAdapter(listPlan)
            binding.rvdietplan.adapter = list
        }
    }
}