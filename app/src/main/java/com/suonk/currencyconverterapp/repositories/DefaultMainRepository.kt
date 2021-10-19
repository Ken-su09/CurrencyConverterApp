package com.suonk.currencyconverterapp.repositories

import com.suonk.currencyconverterapp.models.data.CurrencyResponse
import retrofit2.Response

interface DefaultMainRepository {

    suspend fun getLatestRates(symbols: String): Response<CurrencyResponse>
}