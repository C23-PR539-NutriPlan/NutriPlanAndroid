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
                judulFood.text = dataPlan.name
                maxCalDetail.text = dataPlan.max_cal
                gambarMakanan.setImageResource(dataPlan.photo)
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
        val dataName = resources.getStringArray(R.array.name)
        val dataMax_Cal = resources.getStringArray(R.array.Max_Cal)
        val dataIngredients = resources.getStringArray(R.array.Ingredients)
        val photo = resources.obtainTypedArray(R.array.Photo)
        val listPLAN = ArrayList<Plan>()

        for(i in dataName.indices){
            val PLAN = Plan(dataName[i],dataIngredients[i],dataMax_Cal[i], photo.getResourceId(i,-1))
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