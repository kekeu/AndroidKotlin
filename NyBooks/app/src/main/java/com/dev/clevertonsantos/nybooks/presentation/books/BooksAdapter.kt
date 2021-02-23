package com.dev.clevertonsantos.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.nybooks.R
import com.dev.clevertonsantos.nybooks.data.model.Book

class BooksAdapter(
    private val books: List<Book>,
    val onItemClickerListener: ((book: Book) -> Unit)
    ) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book,
            parent, false)
        return BooksViewHolder(itemView, onItemClickerListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(
        itemView: View,
        val onItemClickerListener: ((book: Book) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.itemBookTitle)
        private val authorTextView = itemView.findViewById<TextView>(R.id.itemBookAuthor)

        fun bindView(book: Book) {
            titleTextView.text = book.title
            authorTextView.text = book.author

            itemView.setOnClickListener {
                onItemClickerListener.invoke(book)
            }
        }
    }
}