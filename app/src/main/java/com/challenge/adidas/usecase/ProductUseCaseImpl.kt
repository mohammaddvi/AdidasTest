package com.challenge.adidas.usecase

import com.challenge.adidas.Product
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.repository.ProductRepository

class ProductUseCaseImpl(
    private val productRepository: ProductRepository,
    private val productDataStore: ProductDataStore
) : ProductUseCase {
    override suspend fun getProductsBySearch(search: String): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(): List<Product> {
        return productRepository.getProducts().also {
            productDataStore.updateProducts(it)
        }
    }

    override suspend fun getProductById(id: String): Product {
        return productRepository.getProductDetails(id)
    }
}