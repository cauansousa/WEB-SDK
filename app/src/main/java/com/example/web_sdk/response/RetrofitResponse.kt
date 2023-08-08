package com.example.web_sdk.response

import android.util.Log
import com.example.web_sdk.service.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitResponse {

    fun sendCallback(text: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.15.29:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(RetrofitService::class.java)

        val response = "BOA"
        apiService.sendResponse(response).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("RESPONSE", "Enviado: $text")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Trate os erros aqui.
            }
        })

    }

}