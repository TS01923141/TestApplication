package com.example.testapplication

import com.example.testapplication.model.RetrofitModel
import com.example.testapplication.model.api.ApiResult
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofitTest(){
        var dataService =
            RetrofitModel.getmInstance().getAPI()
        var call : Call<ApiResult> =
            dataService.getWeatherData("CWB-BECB3D3B-3162-4B21-96A4-4D4276962021" ,"臺北市")
//        call.enqueue(object : Callback<ApiResult>{
//            override fun onResponse(call: Call<ApiResult>, response: Response<ApiResult>) {
//                println(response.isSuccessful)
//                println(response.body()?.success)
//            }
//
//            override fun onFailure(call: Call<ApiResult>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
        var response = call.execute()
        println(response.isSuccessful)
        if (response.isSuccessful){
            println(response.body()?.success)
        }
    }
}
