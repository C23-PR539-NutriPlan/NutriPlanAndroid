package com.example.nutriplan.feature.form


import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.example.nutriplan.ViewModelFactory
import com.example.nutriplan.databinding.ActivityMakeReccomendationBinding
import com.example.nutriplan.feature.mainmenu.MainMenu

class Form : AppCompatActivity() {
    private lateinit var binding: ActivityMakeReccomendationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeReccomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.apply {
            binding.finishrecomm.setOnClickListener {
                getValueForm()
            }
        }
    }

    private fun getValueForm() {
        val viewModelFactory : ViewModelFactory = ViewModelFactory.getInstance(this)
        val formViewModel : FormViewModel by viewModels{ viewModelFactory}

        val gender = if (binding.radioButton.isChecked) {
            binding.radioButton.text.toString().trim()
        } else {
            binding.radioButton2.text.toString().trim()
        }
        val height = binding.insertHeight.text.toString()

        val weight = binding.insertWeight.text.toString()

        val age = binding.AgeForm.text.toString()

        //val calculate : Int = (weight / height/ height) * 10000
        val allergies = getAllergies()
        val preferences = getPreferences()

        if (gender.isEmpty() || binding.insertHeight.text.toString().isEmpty() || binding.insertWeight.text.toString().isEmpty()|| binding.AgeForm.text.toString().isEmpty()||allergies.size <2||preferences.size <2){
            Toast.makeText(this@Form,"Please Check your input",Toast.LENGTH_SHORT).show()
        }
        else{
            val heightInt = height.toInt()
            val weightInt = weight.toInt()
            val ageInt = age.toInt()
            formViewModel.getToken().observe(this@Form){token->
                if(token != null){
                    formViewModel.getID().observe(this){id->
                        if(id != null){
                            formViewModel.postProfile(id,heightInt,weightInt,0, gender,ageInt,allergies,preferences,token).observe(this@Form){form ->
                                Log.e("Pesan",form.toString())
                                when (form){
                                    is com.example.nutriplan.repository.Result.Loading -> {

                                    }
                                    is com.example.nutriplan.repository.Result.Success ->{
                                        Toast.makeText(this@Form,"Succes",Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@Form, MainMenu::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    is com.example.nutriplan.repository.Result.Error ->{
                                        Toast.makeText(this@Form,"Failed Upload Form",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getAllergies() : ArrayList<String>{
        val allergies = ArrayList<String>()
        if (binding.Beef.isChecked) {
            val Beef = binding.Beef.text.toString()
            allergies.add(Beef)
        }
        if (binding.Egg.isChecked) {
            val Egg = binding.Egg.text.toString()
            allergies.add(Egg)
        }
        if (binding.Seafood.isChecked) {
            val Seafood = binding.Seafood.text.toString()
            allergies.add(Seafood)
        }
        if (binding.Chicken.isChecked) {
            val Chicken = binding.Chicken.text.toString()
            allergies.add(Chicken)
        }
        if (binding.Garlic.isChecked) {
            val Garlic = binding.Garlic.text.toString()
            allergies.add(Garlic)
        }
        if (binding.Dairy.isChecked) {
            val Dairy = binding.Dairy.text.toString()
            allergies.add(Dairy)
        }
        if (binding.Peanuts.isChecked) {
            val Peanuts = binding.Peanuts.text.toString()
            allergies.add(Peanuts)
        }
        if (binding.Wheat.isChecked) {
            val Wheat = binding.Wheat.text.toString()
            allergies.add(Wheat)
        }
        if (binding.Soybean.isChecked) {
            val Soybean = binding.Soybean.text.toString()
            allergies.add(Soybean)
        }
        if (binding.Onion.isChecked) {
            val Onion = binding.Onion.text.toString()
            allergies.add(Onion)
        }
        if (binding.Chili.isChecked) {
            val Chili = binding.Chili.text.toString()
            allergies.add(Chili)
        }
        if (binding.Paprika.isChecked) {
            val Paprika = binding.Paprika.text.toString()
            allergies.add(Paprika)
        }
        if (binding.Pork.isChecked) {
            val Pork = binding.Pork.text.toString()
            allergies.add(Pork)
        }
        if (binding.Peppers.isChecked) {
            val Peppers = binding.Peppers.text.toString()
            allergies.add(Peppers)
        }
        if (binding.Rum.isChecked) {
            val Rum = binding.Rum.text.toString()
            allergies.add(Rum)
        }

        if(allergies != null){
            return allergies
        }else{
            val kosong = ArrayList<String>()
            return kosong
        }

    }

    private fun getPreferences() : ArrayList<String> {
        val preference = ArrayList<String>()
        if (binding.SeafoodFav.isChecked) {
            val SeafoodFac = binding.SeafoodFav.text.toString()
            preference.add(SeafoodFac)
        }
        if (binding.BeefFav.isChecked) {
            val BeefFav = binding.BeefFav.text.toString()
            preference.add(BeefFav)
        }
        if (binding.ChickenFav.isChecked) {
            val ChickenFav = binding.ChickenFav.text.toString()
            preference.add(ChickenFav)
        }
        if (binding.Mushroom.isChecked) {
            val Mushroom = binding.Mushroom.text.toString()
            preference.add(Mushroom)
        }
        if (binding.Tofu.isChecked) {
            val Tofu = binding.Tofu.text.toString()
            preference.add(Tofu)
        }
        if (binding.Pasta.isChecked) {
            val Pasta = binding.Pasta.text.toString()
            preference.add(Pasta)
        }
        if (binding.EggFav.isChecked) {
            val EggFav = binding.EggFav.text.toString()
            preference.add(EggFav)
        }
        if (binding.Pastry.isChecked) {
            val Pastry = binding.Pastry.text.toString()
            preference.add(Pastry)
        }
        if (binding.Rice.isChecked) {
            val Rice = binding.Rice.text.toString()
            preference.add(Rice)
        }
//        if (binding.Potatoes.isChecked) {
//            val Potatoes = binding.Potatoes.text.toString()
//            preference.add(Potatoes)
//        }
        if (binding.Cake.isChecked) {
            val Cake = binding.Cake.text.toString()
            preference.add(Cake)
        }

        if(preference != null){
            return preference
        }else{
            val kosong = ArrayList<String>()
            return kosong
        }
    }


}
