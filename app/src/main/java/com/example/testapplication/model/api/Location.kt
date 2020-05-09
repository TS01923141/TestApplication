package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("locationName") var locationName : String,
    @SerializedName("weatherElement") var weatherElement : MutableList<WeatherElement>
)