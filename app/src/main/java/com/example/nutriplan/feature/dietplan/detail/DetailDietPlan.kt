package com.example.nutriplan.feature.dietplan.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nutriplan.R
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityDetailDietPlanBinding
import com.example.nutriplan.databinding.ActivityFaqdetailBinding
import com.example.nutriplan.feature.dietplan.DietPlanViewModel
import com.example.nutriplan.feature.faq.FAQElement
import com.example.nutriplan.model.Plan
import com.example.nutriplan.feature.dietplan.DietPlan.Companion.EXTRA_ID
import com.example.nutriplan.model.Story

class DetailDietPlan : AppCompatActivity() {

    private val viewModelFactory : ViewModelFactory = ViewModelFactory.getInstance(this)
    private val detailPlanViewModel : DetailPlanViewModel by viewModels { viewModelFactory  }
    private lateinit var binding: ActivityDetailDietPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDietPlanBinding.inflate(layoutInflater)

        val foodID = intent.getIntExtra(EXTRA_ID,0)
        setContentView(binding.root)
        detailPlanViewModel.apply {
            getID().observe(this@DetailDietPlan){userID->
                if (userID != null){
                    getSpesificFood(foodID!!,userID).observe(this@DetailDietPlan){
                        when(it){
                            is com.example.nutriplan.repository.Result.Loading ->{

                            }
                            is com.example.nutriplan.repository.Result.Success ->{
                                setDetail(it.data.story)
                                Toast.makeText(this@DetailDietPlan,"Succes to get detail",Toast.LENGTH_SHORT).show()
                            }
                            is com.example.nutriplan.repository.Result.Error ->{
                                Toast.makeText(this@DetailDietPlan,"Failed to get detail",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }

            }
        }
        supportActionBar?.hide()
    }


    private fun setDetail(story: Story){
        binding.apply {
            judulFood.text = story.name
            detailProtein.text = story.proteinContent.toString()
            detailCarbohydrate.text = story.carbohydrateContent.toString()
            detailFat.text = story.fatContent.toString()
            detailFiber.text = story.fiberContent.toString()
            detailCholestrol.text = story.cholesterolContent.toString()
            detailSugar.text = story.sugarContent.toString()
            likedFood.isChecked = story.like

            Glide.with(this@DetailDietPlan).load(story.image).centerCrop().into(gambarMakanan)
        }
    }






}