package com.dev.clevertonsantos.mybeats.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val senha: String
)