package com.example.web_sdk.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/responses")
    fun sendResponse(@Query("message") response: String): Call<ResponseBody>
}
