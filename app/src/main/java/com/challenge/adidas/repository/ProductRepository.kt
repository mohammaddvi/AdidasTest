package com.challenge.adidas.repository

import com.challenge.adidas.Product
import com.challenge.adidas.Review

interface ProductRepository {

    suspend fun getProducts(): List<Product>
    suspend fun getProductDetails(id:String): Product
    suspend fun sendReview(id:String, review: Review):Review
}
