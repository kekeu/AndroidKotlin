package com.dev.clevertonsantos.nybooks.presentation.books

import android.os.Bundle
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.nybooks.R
import com.dev.clevertonsantos.nybooks.data.repository.BooksApiDataSource
import com.dev.clevertonsantos.nybooks.presentation.base.BaseActivity
import com.dev.clevertonsantos.nybooks.presentation.details.BookDetailsActivity

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        setUpToolbar(toolbarMain, R.string.books)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
                .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, {
            it?.let { books ->
                val recyclerBooks = findViewById<RecyclerView>(R.id.recyclerBooks)
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity,
                        RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity, book)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.viewFlipperLiveData.observe(this, {
            it?.let { viewFlipper ->
                val flipper = findViewById<ViewFlipper>(R.id.booksFlipper)
                flipper.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessaResId ->
                    findViewById<TextView>(R.id.booksError).text = getString(errorMessaResId)
                }
            }
        })
        viewModel.getBooks()
    }
}