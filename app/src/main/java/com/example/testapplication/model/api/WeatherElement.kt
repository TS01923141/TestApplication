package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class WeatherElement(
    @SerializedName("elementName") var elementName : String,
    @SerializedName("time") var time : MutableList<Time>
)