package com.example.testapplication.model

import com.example.testapplication.model.api.ApiResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*
import java.util.concurrent.TimeUnit

class RetrofitModel {
    companion object {
        private val BASE_URL = "http://opendata.cwb.gov.tw/api/"

        private lateinit var mInstance: RetrofitModel
        private lateinit var dataService: DataService

        @JvmStatic
        fun getmInstance(): RetrofitModel {
            if (!this::mInstance.isInitialized) {
                mInstance = RetrofitModel()
            }
            return mInstance
        }
    }

    public interface DataService {

        @GET("v1/rest/datastore/F-C0032-001")
        fun getWeatherData(
            @Query("Authorization") authorization: String,
            @Query("locationName") locationName: String
        ): Call<ApiResult>
    }

    //設置timeout
    internal var okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .retryOnConnectionFailure(true)
        .build()

    constructor() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        dataService = retrofit.create<DataService>(DataService::class.java)
    }

    fun getAPI(): DataService {
        return dataService
    }
}