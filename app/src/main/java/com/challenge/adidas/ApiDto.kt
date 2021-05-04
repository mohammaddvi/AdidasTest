package com.challenge.adidas

import androidx.annotation.Keep

@Keep
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imgUrl: String,
    val reviews: List<Review>
)

@Keep
data class Review(
    val productId: String,
    val locale: String,
    val rating: Int,
    val imgUrl: String,
    val text: String
)
