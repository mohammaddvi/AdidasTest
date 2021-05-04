package com.challenge.adidas.repository

import android.util.Log
import com.challenge.adidas.Product
import com.challenge.adidas.network.ProductApi

class RemoteProductRepository(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productApi.getProducts()

    }

    override suspend fun getProductDetails(id: String): Product {
        return productApi.getProductDetails(id)
    }
}