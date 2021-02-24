package com.dev.clevertonsantos.nybooks.di

import com.dev.clevertonsantos.nybooks.data.repository.BooksApiDataSource
import com.dev.clevertonsantos.nybooks.presentation.books.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val booksModule = module {
    single {
        BooksApiDataSource()
    }

    viewModel {
        BooksViewModel(
            get<BooksApiDataSource>()
        )
    }
}