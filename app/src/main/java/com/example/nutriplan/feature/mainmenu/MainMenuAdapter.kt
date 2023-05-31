package com.example.nutriplan.feature.mainmenu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriplan.R
import com.example.nutriplan.model.Plan

class MainMenuAdapter(private  val listPLAN : ArrayList<Plan>): RecyclerView.Adapter<MainMenuAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val food : TextView = itemView.findViewById(R.id.item_rv_kecil)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_main_menu_,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPLAN.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val  (day,foodla,calories) = listPLAN[position]
        holder.food.text = foodla

    }
}