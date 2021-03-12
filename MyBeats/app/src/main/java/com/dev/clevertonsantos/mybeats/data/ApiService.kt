package com.dev.clevertonsantos.mybeats.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://20.195.184.239/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: HeadphoneService = initRetrofit().create(HeadphoneService::class.java)
}