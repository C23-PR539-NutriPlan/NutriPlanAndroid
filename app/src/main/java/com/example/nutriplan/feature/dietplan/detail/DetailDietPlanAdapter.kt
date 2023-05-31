package com.example.nutriplan.feature.dietplan.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriplan.R
import com.example.nutriplan.model.Plan

class DetailDietPlanAdapter(private  val listPLAN : ArrayList<Plan>): RecyclerView.Adapter<DetailDietPlanAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val food :TextView = itemView.findViewById(R.id.FoodDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_dietplan,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPLAN.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val  (day,foodla,calories) = listPLAN[position]

        holder.food.text = foodla


    }
}