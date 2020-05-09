package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class Field(
    @SerializedName("id") var id : String = "",
    @SerializedName("type") var String : String = ""
)