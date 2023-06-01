package com.example.nutriplan.feature.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityMainMenuBinding
import com.example.nutriplan.feature.dietplan.DietPlan
import com.example.nutriplan.feature.faq.FAQ
import com.example.nutriplan.feature.faq.FAQElement
import com.example.nutriplan.feature.faq.FAQuestionAdapter
import com.example.nutriplan.feature.makereccomendation.MakeReccomendation
import com.example.nutriplan.feature.profile.Profile
import com.example.nutriplan.model.Plan

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.menuProfile.setOnClickListener {
                val intent = Intent(this@MainMenu, Profile::class.java)
                startActivity(intent)
            }
            binding.MenuGenerate.setOnClickListener {
                val intent = Intent(this@MainMenu, MakeReccomendation::class.java)
                startActivity(intent)
            }
            binding.menuRecommendation.setOnClickListener {
                val intent = Intent(this@MainMenu, DietPlan::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_utama,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.FAQMenu->{
                val intent = Intent(this@MainMenu, FAQ::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

