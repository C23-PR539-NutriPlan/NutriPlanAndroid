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
        supportActionBar?.hide()
    }






    companion object{
        const val KEY_PLAN = "key_Plan"
    }
}