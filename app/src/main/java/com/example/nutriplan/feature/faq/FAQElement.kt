package com.example.nutriplan.feature.faq

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FAQElement(
    val question : String,
    val answer : String
):Parcelable