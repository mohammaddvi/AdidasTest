package com.challenge.adidas.datastore

import com.challenge.adidas.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataStore {
    fun products(): List<Product>
    fun updateProducts(products: List<Product>)
    fun updateProduct(product:Product)
}