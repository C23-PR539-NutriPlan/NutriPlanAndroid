package com.example.nutriplan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plan(
    val name:String,
    val ingredients:String,
    val max_cal:String,
    val photo:Int
):Parcelable
