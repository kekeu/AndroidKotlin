package com.dev.clevertonsantos.nybooks.data.repository

import com.dev.clevertonsantos.nybooks.data.ApiService
import com.dev.clevertonsantos.nybooks.data.BooksResult
import com.dev.clevertonsantos.nybooks.data.model.Book
import com.dev.clevertonsantos.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        ApiService.service.listBooks().enqueue(object: Callback<BookBodyResponse> {
            override fun onResponse(
                    call: Call<BookBodyResponse>,
                    response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        for (bookResultResponse in bookBodyResponse.bookResults) {
                            books.add(
                                    Book(
                                            title = bookResultResponse.bookDetails[0].title,
                                            author = bookResultResponse.bookDetails[0].author,
                                            description = bookResultResponse.bookDetails[0].descriptor
                                    )
                            )
                        }
                    }
                    booksResultCallback(BooksResult.Success(books))
                } else {
                    booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }
        })
    }
}