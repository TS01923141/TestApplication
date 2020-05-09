package com.example.testapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.model.RetrofitModel
import com.example.testapplication.model.api.ApiResult
import com.example.testapplication.model.api.Time
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    companion object {
        const val STATUS = "status"
        const val FIRST_OPEN = "first open"
    }

    private val TAG = MainActivity::class.simpleName

    private lateinit var call: Call<ApiResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences(STATUS, Context.MODE_PRIVATE)
        var isFirstOpen = sharedPreferences.getBoolean(FIRST_OPEN, true)
        if (isFirstOpen) {
            sharedPreferences.edit()
                .putBoolean(FIRST_OPEN, false)
                .commit()
        } else {
            Toast.makeText(applicationContext, "歡迎回來", Toast.LENGTH_LONG).show()
        }

        var layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView_main.layoutManager = layoutManager

        var dataService = RetrofitModel.getmInstance().getAPI()
        call = dataService.getWeatherData("CWB-BECB3D3B-3162-4B21-96A4-4D4276962021", "臺北市")

        call.enqueue(object : Callback<ApiResult> {
            override fun onResponse(call: Call<ApiResult>, response: Response<ApiResult>) {
                var timeList: MutableList<Time> = arrayListOf()
                var locationList = response.body()!!.records.location
                for (locataion in locationList) {
                    for (weatherElement in locataion.weatherElement) {
                        if (weatherElement.elementName == "MinT") {
                            timeList = weatherElement.time
                        }
                    }
                }
                var mainAdapter = MainAdapter(timeList)
                recyclerView_main.adapter = mainAdapter
            }

            override fun onFailure(call: Call<ApiResult>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onDestroy() {
        if (this::call.isInitialized && call.isExecuted) {
            call.cancel()
        }
        super.onDestroy()
    }
}
