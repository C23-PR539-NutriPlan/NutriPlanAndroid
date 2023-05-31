package com.example.nutriplan.feature.dietplan.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityDetailDietPlanBinding
import com.example.nutriplan.databinding.ActivityFaqdetailBinding
import com.example.nutriplan.feature.faq.FAQElement
import com.example.nutriplan.model.Plan

class DetailDietPlan : AppCompatActivity() {
    val listPlan  = ArrayList<Plan>()
    private lateinit var binding: ActivityDetailDietPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataPlan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra( KEY_PLAN, Plan::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_PLAN)
        }

        if (dataPlan != null){
            binding.apply {
                judulDay.text = dataPlan.day
                maxCalDetail.text = dataPlan.max_cal
            }
            binding.rvDetailDietPlan.setHasFixedSize(true)
            if (binding.rvDetailDietPlan.size == 0){
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
            binding.rvDetailDietPlan.layoutManager = LinearLayoutManager(this)
            val list = DetailDietPlanAdapter(listPlan)
            binding.rvDetailDietPlan.adapter = list
        }
    }

    companion object{
        const val KEY_PLAN = "key_Plan"
    }
}