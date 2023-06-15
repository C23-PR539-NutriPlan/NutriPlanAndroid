package com.example.nutriplan.feature.dietplan

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ItemDietplanBinding
import com.example.nutriplan.feature.dietplan.detail.DetailDietPlan
import com.example.nutriplan.model.ListStoryItem
import com.example.nutriplan.model.Plan

class DietPlanAdapter(private val listPLAN: List<ListStoryItem>) : RecyclerView.Adapter<DietPlanAdapter.ViewHolder>() {

    private lateinit var onItemClick: OnitemClick

    interface OnitemClick {
        fun onItemClicked(data: ListStoryItem)
    }

    fun setOnItemClick(onItemClick: OnitemClick) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(private val binding : ItemDietplanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food :ListStoryItem){
            binding.root.setOnClickListener {
                onItemClick!!.onItemClicked(food)
            }
            binding.apply {
                FoodName.text = food.name
                Calories.text = food.calories.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDietplanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int  = listPLAN.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPLAN[position])
    }




}
