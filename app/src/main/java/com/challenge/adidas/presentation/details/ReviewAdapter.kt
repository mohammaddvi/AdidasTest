package com.challenge.adidas.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.adidas.Product
import com.challenge.adidas.R
import com.challenge.adidas.Review
import com.challenge.adidas.common.load
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter :
    ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(ProductDiffCallback) {
    private val items = mutableListOf<Review>()

    object ProductDiffCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.productId == newItem.productId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateAdapter(newItems: List<Review>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(review: Review) {
            itemView.reviewRate.rating = review.rating.toFloat()
            itemView.reviewText.text= review.text
        }
    }
}
