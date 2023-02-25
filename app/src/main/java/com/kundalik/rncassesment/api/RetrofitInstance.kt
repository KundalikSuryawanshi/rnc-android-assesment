package com.kundalik.rncassesment.api

import com.kundalik.rncassesment.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private val client = OkHttpClient.Builder().apply {
            addInterceptor(ProductInterceptor())
        }.build()

        private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: ProductApi by lazy {
            retrofit.create(ProductApi::class.java)
        }

    }
}