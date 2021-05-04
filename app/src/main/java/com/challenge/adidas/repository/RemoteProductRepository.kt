package com.challenge.adidas.repository

import com.challenge.adidas.Product
import com.challenge.adidas.network.ProductApi
import com.challenge.adidas.toProductModel

class RemoteProductRepository(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productApi.getProducts().map { it.toProductModel() }

    }

    override suspend fun getProductDetails(id: String): Product {
        return productApi.getProductDetails(id).toProductModel()
    }
}