package com.challenge.adidas.datastore

import com.challenge.adidas.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class InMemoryProductDataStoer : ProductDataStore {
    private val productsFlow: MutableStateFlow<List<Product>?> = MutableStateFlow(null)

    override fun products(): Flow<List<Product>> {
        return productsFlow.filterNotNull()
    }

    override fun updateProducts(products: List<Product>) {
        productsFlow.value = products
    }
}