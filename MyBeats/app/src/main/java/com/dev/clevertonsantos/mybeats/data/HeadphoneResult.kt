package com.dev.clevertonsantos.mybeats.data

import com.dev.clevertonsantos.mybeats.data.model.Headphone

sealed class HeadphoneResult {
    class Success(val headphones: List<Headphone>?) : HeadphoneResult()
    class ApiError(val messageError: String) : HeadphoneResult()
    object ServerError : HeadphoneResult()
}