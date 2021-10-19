package com.suonk.currencyconverterapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterApiService {

    companion object {
        const val BASE_URL = "http://api.exchangeratesapi.io/"
        const val API_KEY = "cf552def756d607417ad1ee78475b2c9"
    }

    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apiKey")
        apiKey: String = API_KEY
    )
}