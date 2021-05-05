package com.challenge.adidas

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imgUrl: String?,
    val averageReviewStar: Float,
    val reviews: List<Review>
)

data class Review(
    val productId: String,
    val locale: String?,
    val rating: Int,
    val imgUrl: String? = null,
    val text: String
)