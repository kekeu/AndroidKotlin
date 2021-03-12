package com.dev.clevertonsantos.mybeats.data

import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import retrofit2.Call
import retrofit2.http.GET

interface HeadphoneService {

    @GET("beats")
    fun listHeadphones(): Call<List<HeadphoneResponse>>
}