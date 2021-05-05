package com.challenge.adidas.datastore

import com.challenge.adidas.Product

class InMemoryProductDataStore : ProductDataStore {
    private val productsFlow: MutableList<Product> = mutableListOf()

    override fun products(): List<Product> {
        return productsFlow
    }

    override fun updateProducts(products: List<Product>) {
        productsFlow.clear()
        productsFlow.addAll(products)
    }

    override fun updateProduct(product: Product) {
        productsFlow.first { product.id == it.id }.copy(
            name = product.name,
            description = product.description,
            price = product.price,
            reviews = product.reviews
        )
    }
}