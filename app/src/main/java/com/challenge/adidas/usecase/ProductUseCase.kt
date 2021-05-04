package com.challenge.adidas.usecase

import com.challenge.adidas.Product

interface ProductUseCase {
    fun getProductsBySearch(search: String): List<Product>
    fun getProducts(): List<Product>
    fun getProductById(id: String): Product
}