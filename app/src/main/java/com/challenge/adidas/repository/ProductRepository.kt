package com.challenge.adidas.repository

import com.challenge.adidas.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>
    suspend fun getProductDetails(id:String): Product
}
