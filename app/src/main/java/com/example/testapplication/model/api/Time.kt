package com.example.testapplication.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Time(
    @SerializedName("startTime") var startTime : String,
    @SerializedName("endTime") var endTime : String,
    @SerializedName("parameter") var parameter : Parameter
): Parcelable