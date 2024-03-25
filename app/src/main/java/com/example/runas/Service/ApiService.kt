package com.example.runas.Service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("endpoint")
    fun getPeticionGet(): Call<Response>

    @POST("endpoint")
    fun postPeticionPost(@Body datos: Datos): Call<Response>
}