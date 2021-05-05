package com.challenge.adidas.repository

import com.challenge.adidas.*
import com.challenge.adidas.network.ProductApi
import com.challenge.adidas.network.ReviewApi

class RemoteProductRepository(private val productApi: ProductApi,private val reviewApi: ReviewApi) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productApi.getProducts().map { it.toProduct() }

    }

    override suspend fun getProductDetails(id: String): Product {
        return productApi.getProductDetails(id).toProduct()
    }

    override suspend fun sendReview(id: String,review: Review): Review {
        return reviewApi.sendReviewById(id,review.toReviewDto()).toReview()
    }
}