package com.challenge.adidas.datastore

import com.challenge.adidas.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataStore {
    fun products(): Flow<List<Product>>
    fun updateProducts(products: List<Product>)
//    fun productById(id:String):Product
}