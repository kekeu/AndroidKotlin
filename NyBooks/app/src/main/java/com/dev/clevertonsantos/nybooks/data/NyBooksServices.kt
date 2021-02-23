package com.dev.clevertonsantos.nybooks.data

import com.dev.clevertonsantos.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NyBooksServices {

    @GET("lists.json")
    fun listBooks(
        @Query("api-key") apiKey: String = "FH7JTJ8iyr9DfGNBKmalxf46NWCKpVjl",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>

}