package com.example.web_sdk.service

import com.example.web_sdk.model.RespostaServidor
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("image")
    fun enviarImagem(@Part imagem: MultipartBody.Part): Call<RespostaServidor>
}