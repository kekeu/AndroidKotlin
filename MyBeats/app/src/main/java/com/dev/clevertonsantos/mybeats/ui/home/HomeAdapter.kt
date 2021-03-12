package com.dev.clevertonsantos.mybeats.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import java.text.DecimalFormat

class HomeAdapter(
        private val headphones: List<Headphone>,
        private val onItemClickerListener: ((headphone: Headphone) -> Unit)
    ) : RecyclerView.Adapter<HomeAdapter.HeadphonesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadphonesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent, false)
        return HeadphonesViewHolder(itemView, onItemClickerListener)
    }

    override fun onBindViewHolder(holder: HeadphonesViewHolder, position: Int) {
        holder.bindView(headphones[position])
    }

    override fun getItemCount() = headphones.count()

    class HeadphonesViewHolder(
        itemView: View,
        private val onItemClickerListener: ((headphone: Headphone) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val itemDescription = itemView.findViewById<TextView>(R.id.itemDescription)
        private val itemNote = itemView.findViewById<TextView>(R.id.itemNote)
        private val itemReviews = itemView.findViewById<TextView>(R.id.itemReviews)
        private val itemValue = itemView.findViewById<TextView>(R.id.itemValue)
        private val imageView = itemView.findViewById<ImageView>(R.id.itemImagem)

        fun bindView(headphone: Headphone) {
            itemDescription.text = headphone.name
            itemNote.text = headphone.rating.toString()
            itemReviews.text = "${headphone.total_reviews} Reviews"
            val dec = DecimalFormat("#,###.00")
            itemValue.text = "R$ ${dec.format(headphone.value)}"
            Glide.with(itemView.context).load(headphone.image).into(imageView)

            itemView.setOnClickListener {
                onItemClickerListener.invoke(headphone)
            }
        }
    }
}