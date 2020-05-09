package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("datasetDescription") var datasetDescription : String,
    @SerializedName("location") var location : MutableList<Location>
)