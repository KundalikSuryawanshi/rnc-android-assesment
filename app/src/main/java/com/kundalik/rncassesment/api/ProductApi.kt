package com.kundalik.rncassesment.api

import com.kundalik.rncassesment.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("/api/v1/products.json")
    suspend fun getProducts(
        @Query("brand") brand: String

    ): Response<List<Product>>

}