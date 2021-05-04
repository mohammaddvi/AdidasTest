package com.challenge.adidas.network

import com.challenge.adidas.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("product")
    suspend fun getProducts(): List<Product>

    @GET("product/{id}")
    suspend fun getProductDetails(@Path("id") id: String): Product

}