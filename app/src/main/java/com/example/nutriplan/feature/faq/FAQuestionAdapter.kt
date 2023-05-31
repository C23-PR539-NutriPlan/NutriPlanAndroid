package com.example.nutriplan.feature.faq

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriplan.R


class FAQuestionAdapter(private  val listFAQ : ArrayList<FAQElement>): RecyclerView.Adapter<FAQuestionAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val question : TextView = itemView.findViewById(R.id.questionFAQ)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_faq,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFAQ.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val  (questionaja,answer) = listFAQ[position]
        holder.question.text = questionaja

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context,FAQDetail::class.java)
            intentDetail.putExtra("key_FAQ",listFAQ[position])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}