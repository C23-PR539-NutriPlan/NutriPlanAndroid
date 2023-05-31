package com.example.nutriplan.feature.faq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityFaqBinding

class FAQ : AppCompatActivity() {
    val listfAQ  = ArrayList<FAQElement>()
    private lateinit var binding: ActivityFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            binding.rvFAQuestion.setHasFixedSize(true)
            if (binding.rvFAQuestion.size == 0){
                listfAQ.addAll(getlistFAQ())
            }
        }
        showListQuestion()
    }

    private fun getlistFAQ():ArrayList<FAQElement>{
        val dataQuestion = resources.getStringArray(R.array.FAQuestion)
        val dataAnswer = resources.getStringArray(R.array.FAQAnswer)
        val listFAQ  = ArrayList<FAQElement>()

        for(i in dataQuestion.indices){
            val fAQ = FAQElement(dataQuestion[i],dataAnswer[i])
            listFAQ.add(fAQ)
        }
        return listFAQ
    }

    private fun showListQuestion(){
        for (i in  0 .. 3){
            binding.rvFAQuestion.layoutManager = LinearLayoutManager(this)
            val listfaQ = FAQuestionAdapter(listfAQ)
            binding.rvFAQuestion.adapter = listfaQ
        }
    }
}