package com.challenge.adidas.network

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {
    @POST("reviews/{id}")
    suspend fun sendReviewById(@Path("id") id: String, @Body reviewDto: ReviewDto): ReviewDto

}