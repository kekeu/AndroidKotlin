package com.dev.clevertonsantos.nybooks.data

import com.dev.clevertonsantos.nybooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}