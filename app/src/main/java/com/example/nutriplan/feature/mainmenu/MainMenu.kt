package com.example.nutriplan.feature.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityMainMenuBinding
import com.example.nutriplan.feature.dietplan.DietPlan
import com.example.nutriplan.feature.faq.FAQ
import com.example.nutriplan.feature.faq.FAQElement
import com.example.nutriplan.feature.faq.FAQuestionAdapter
import com.example.nutriplan.feature.profile.Profile
import com.example.nutriplan.model.Plan

class MainMenu : AppCompatActivity() {
    val listPlan  = ArrayList<Plan>()
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.berandaProfile.setOnClickListener {
                val intent = Intent(this@MainMenu, com.example.nutriplan.feature.profile.Profile::class.java)
                startActivity(intent)
            }
            binding.berandaFAQ.setOnClickListener {
                val intent = Intent(this@MainMenu, FAQ::class.java)
                startActivity(intent)
            }
            binding.berandadiet.setOnClickListener {
                val intent = Intent(this@MainMenu, DietPlan::class.java)
                startActivity(intent)
            }
            binding.rvKecil.setHasFixedSize(true)
            if (binding.rvKecil.size == 0){
                listPlan.addAll(getlistPlan())
            }
        }
        showListPlan()

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
            binding.rvKecil.layoutManager = LinearLayoutManager(this)
            val list = MainMenuAdapter(listPlan)
            binding.rvKecil.adapter = list
        }
    }
}

