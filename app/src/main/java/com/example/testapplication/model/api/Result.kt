package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("resource_id") var resourceId : String,
    @SerializedName("fields") var fields : MutableList<Field>
)