package com.challenge.adidas.network

import androidx.annotation.Keep

@Keep
data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imgUrl: String?,
    val reviews: List<ReviewDto>
)

@Keep
data class ReviewDto(
    val productId: String,
    val locale: String,
    val rating: Int,
    val imgUrl: String?,
    val text: String
)
