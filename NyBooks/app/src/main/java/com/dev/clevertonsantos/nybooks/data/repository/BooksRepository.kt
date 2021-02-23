package com.dev.clevertonsantos.nybooks.data.repository

import com.dev.clevertonsantos.nybooks.data.BooksResult

interface BooksRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}