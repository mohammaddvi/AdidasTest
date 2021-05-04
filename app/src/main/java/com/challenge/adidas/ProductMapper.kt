package com.challenge.adidas

import com.challenge.adidas.network.ProductDto
import com.challenge.adidas.network.ReviewDto

fun ProductDto.toProductModel() = Product(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    imgUrl = this.imgUrl,
    reviews = this.reviews.map { it.toReviewModel() }
)

fun ReviewDto.toReviewModel() = Review(
    productId = this.productId,
    rating = this.rating,
    imgUrl = this.imgUrl,
    text = this.text
)