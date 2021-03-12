package com.dev.clevertonsantos.mybeats.data

import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import com.dev.clevertonsantos.mybeats.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HeadphoneService {

    @GET("beats")
    fun listHeadphones(): Call<List<HeadphoneResponse>>

    @POST("login")
    fun login(@Body user: UserResponse): Call<Void>
}