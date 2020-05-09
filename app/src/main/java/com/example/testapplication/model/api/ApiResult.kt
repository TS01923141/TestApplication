package com.example.testapplication.model.api

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("success") var success: String,
    @SerializedName("result") var result: Result,
    @SerializedName("records") var records: Record
)