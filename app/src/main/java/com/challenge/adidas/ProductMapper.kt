package com.challenge.adidas

import com.challenge.adidas.network.ProductDto
import com.challenge.adidas.network.ReviewDto

fun ProductDto.toProduct() = Product(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    imgUrl = this.imgUrl,
    averageReviewStar = this.reviews.map { it.rating }.average().toFloat(),
    reviews = this.reviews.map { it.toReview() }
)

fun ReviewDto.toReview() = Review(
    productId = this.productId,
    locale = this.locale,
    rating = this.rating,
    imgUrl = this.imgUrl,
    text = this.text
)

fun Review.toReviewDto() = ReviewDto(
    productId = this.productId,
    locale = this.locale,
    rating = this.rating,
    text = this.text,
    imgUrl = null
)