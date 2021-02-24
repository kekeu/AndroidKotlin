package com.dev.clevertonsantos.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.clevertonsantos.nybooks.R
import com.dev.clevertonsantos.nybooks.data.BooksResult
import com.dev.clevertonsantos.nybooks.data.model.Book
import com.dev.clevertonsantos.nybooks.data.repository.BooksRepository

class BooksViewModel(val dataSource: BooksRepository) : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        dataSource.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_401)
                    } else {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR,
                                R.string.error_not_mapped)
                    }
                }
                else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_network)
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}