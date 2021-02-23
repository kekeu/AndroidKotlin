package com.dev.clevertonsantos.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.dev.clevertonsantos.nybooks.R
import com.dev.clevertonsantos.nybooks.data.model.Book
import com.dev.clevertonsantos.nybooks.presentation.base.BaseActivity

class BookDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setUpToolbar(toolbar, R.string.book, true)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        val titleText = findViewById<TextView>(R.id.bookDetailsTitle)
        val authorText = findViewById<TextView>(R.id.bookDetailsAuthor)
        val descriptionText = findViewById<TextView>(R.id.bookDetailsDescription)

        titleText.text = title
        authorText.text = author
        descriptionText.text = description
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_AUTHOR = "EXTRA_AUTHOR"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, book: Book): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, book.title)
                putExtra(EXTRA_AUTHOR, book.author)
                putExtra(EXTRA_DESCRIPTION, book.description)
            }
        }
    }
}