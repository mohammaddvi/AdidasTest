package com.challenge.adidas.usecase

import com.challenge.adidas.Product

interface ProductUseCase {
    suspend fun getProductsBySearch(search: String): List<Product>
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: String): Product
}