package com.dev.clevertonsantos.mybeats.data.repository

import com.dev.clevertonsantos.mybeats.data.HeadphoneResult

interface HeadphoneRepository {

    fun getHeadphones(resultCallback: (result: HeadphoneResult) -> Unit)

}