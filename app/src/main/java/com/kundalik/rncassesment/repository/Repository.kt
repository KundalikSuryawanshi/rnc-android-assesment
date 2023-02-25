package com.kundalik.rncassesment.repository

import com.kundalik.rncassesment.api.RetrofitInstance
import com.kundalik.rncassesment.model.Product
import retrofit2.Response

class Repository {

    suspend fun getProducts(brand: String): Response<List<Product>> {
        return RetrofitInstance.api.getProducts(brand)
    }
}