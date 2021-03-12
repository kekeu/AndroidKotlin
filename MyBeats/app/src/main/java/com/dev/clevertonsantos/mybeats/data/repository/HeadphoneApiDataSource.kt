package com.dev.clevertonsantos.mybeats.data.repository

import com.dev.clevertonsantos.mybeats.data.ApiService
import com.dev.clevertonsantos.mybeats.data.HeadphoneResult
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import com.dev.clevertonsantos.mybeats.data.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadphoneApiDataSource : HeadphoneRepository {

    override fun getHeadphones(headphoneResultCallback: (result: HeadphoneResult) -> Unit) {
        ApiService.service.listHeadphones().enqueue(object: Callback<List<HeadphoneResponse>> {
            override fun onResponse(
                call: Call<List<HeadphoneResponse>>,
                response: Response<List<HeadphoneResponse>>
            ) {
                if (response.isSuccessful) {
                    val headphones: MutableList<Headphone> = mutableListOf()

                    response.body()?.let { listHeadphoneResponse ->
                        for (item in listHeadphoneResponse) {
                            headphones.add(
                                Headphone(name = item.name, rating = item.rating,
                                    value = item.value, total_reviews = item.total_reviews,
                                    image = item.image, connection = item.connection,
                                    compatibility = item.compatibility, charge = item.charge,
                                    autonomy = item.autonomy, height = item.height,
                                    capture = item.capture)
                            )
                        }
                    }
                    headphoneResultCallback(HeadphoneResult.Success(headphones))
                } else {
                    headphoneResultCallback(HeadphoneResult.ApiError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<HeadphoneResponse>>, t: Throwable) {
                headphoneResultCallback(HeadphoneResult.ServerError)
            }
        })
    }

    override fun login(
        email: String,
        senha: String,
        resultCallback: (result: HeadphoneResult) -> Unit
    ) {
        ApiService.service.login(UserResponse(email, senha)).enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    resultCallback(HeadphoneResult.Success(null))
                } else {
                    resultCallback(HeadphoneResult.ApiError(response.message()))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                resultCallback(HeadphoneResult.ServerError)
            }
        })
    }
}