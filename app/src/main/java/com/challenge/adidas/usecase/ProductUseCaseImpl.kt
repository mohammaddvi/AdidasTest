package com.challenge.adidas.usecase

import com.challenge.adidas.Product
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.repository.ProductRepository

class ProductUseCaseImpl(
    private val productDataStore: ProductDataStore,
    private val productRepository: ProductRepository
) : ProductUseCase {
    override fun getProductsBySearch(search: String): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: String): Product {
        TODO("Not yet implemented")
    }
}