package com.challenge.adidas.datastore

import com.challenge.adidas.Product
import kotlinx.coroutines.flow.Flow

class InMemoryProductDataStore : ProductDataStore {
    private val productsFlow: MutableList<Product> = mutableListOf()

    override fun products(): List<Product> {
        return productsFlow
    }

    override fun updateProducts(products: List<Product>) {
        productsFlow.clear()
        productsFlow.addAll(products)
    }
}