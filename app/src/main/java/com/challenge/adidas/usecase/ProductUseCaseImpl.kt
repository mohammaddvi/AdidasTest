package com.challenge.adidas.usecase

import com.challenge.adidas.Product
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.repository.ProductRepository

class ProductUseCaseImpl(
    private val productRepository: ProductRepository,
    private val productDataStore: ProductDataStore
) : ProductUseCase {
    override suspend fun getProductsBySearch(search: String): List<Product> {
        return productDataStore.products().filter { product ->
            product.name.contains(search.toLowerCase()) or product.description.contains(search.toLowerCase())
        }
    }

    override suspend fun getProducts(): List<Product> {
        return productRepository.getProducts().also {
            productDataStore.updateProducts(it)
        }
    }

    override suspend fun getProductById(id: String): Product {
        return productDataStore.products().first { it.id == id }.also {
            productRepository.getProductDetails(id).also {
                productDataStore.updateProduct(it)
            }
        }
    }
}