package com.example.nutriplan.feature.faq

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriplan.R
import com.example.nutriplan.databinding.ActivityFaqdetailBinding

class FAQDetail : AppCompatActivity() {
    private lateinit var binding: ActivityFaqdetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataFAQ = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_FAQ,FAQElement::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_FAQ)
        }

        if (dataFAQ != null){
            binding.apply {
                questionFAQDetail.text = dataFAQ.question
                answerFAQDetail.text = dataFAQ.answer
            }
        }
        supportActionBar?.hide()
    }



    companion object{
        const val KEY_FAQ = "key_FAQ"
    }
}