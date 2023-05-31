package com.example.nutriplan.feature.dietplan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriplan.R
import com.example.nutriplan.feature.dietplan.detail.DetailDietPlan
import com.example.nutriplan.model.Plan

class DietPlanAdapter(private  val listPLAN : ArrayList<Plan>): RecyclerView.Adapter<DietPlanAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cal : TextView = itemView.findViewById(R.id.maxCalPlan)
        val day : TextView = itemView.findViewById(R.id.dayplan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dietplan,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPLAN.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val  (day,foodla,calories) = listPLAN[position]
        holder.day.text = day
        holder.cal.text = calories

        holder.itemView.setOnClickListener{
            val intentDetail  = Intent(holder.itemView.context,DetailDietPlan::class.java)
            intentDetail.putExtra("key_Plan",listPLAN[position])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}