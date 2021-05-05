package com.challenge.adidas.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.challenge.adidas.Product
import com.challenge.adidas.R
import com.challenge.adidas.common.load
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val onClick: (Product,ImageView) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback) {
    private val items = mutableListOf<Product>()

    object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position]) { onClick(items[position],it) }
    }

    fun updateAdapter(newItems: List<Product>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product, onProductClicked: (ImageView) -> Unit) {

            itemView.productNameItemText.text = product.name
            itemView.productDescriptionItemText.text = product.description
            itemView.productPriceItemText.text = String.format("%s %s","$",product.price)
            itemView.productOveralRate.rating = product.averageReviewStar
            itemView.productItemImage.load(
                product.imgUrl
            )
            itemView.setOnClickListener {
                onProductClicked(itemView.productItemImage)
            }
        }
    }
}
