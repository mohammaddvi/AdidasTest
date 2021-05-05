package com.challenge.adidas.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @GET("product")
    suspend fun getProducts(): List<ProductDto>

    @GET("product/{id}")
    suspend fun getProductDetails(@Path("id") id: String): ProductDto


}