package com.example.testapplication.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parameter(
    @SerializedName("parameterName") var parameterName : String = "",
    @SerializedName("parameterValue") var parameterValue : String = "",
    @SerializedName("parameterUnit") var parameterUnit : String = ""
): Parcelable