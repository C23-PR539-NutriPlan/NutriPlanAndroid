package com.example.nutriplan.feature.dietplan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutriplan.R
import com.example.nutriplan.feature.dietplan.detail.DetailDietPlan
import com.example.nutriplan.model.ListStoryItem
import com.example.nutriplan.model.Plan

class DietPlanAdapter(private  val listPLAN : List<ListStoryItem>): RecyclerView.Adapter<DietPlanAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cal : TextView = itemView.findViewById(R.id.Calories)
        val nama : TextView = itemView.findViewById(R.id.FoodName)
        val gambar : ImageView = itemView.findViewById(R.id.gambarlistMakanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dietplan,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPLAN.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food =listPLAN[position]

        holder.apply {
            Glide.with(itemView.context).load(food?.image).centerCrop().into(gambar)
            nama.text = food?.name
            cal.text = food?.calories.toString()
        }
    }
}