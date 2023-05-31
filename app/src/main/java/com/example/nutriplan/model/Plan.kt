package com.example.nutriplan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plan(
    val day:String,
    val food:String,
    val max_cal:String
):Parcelable
