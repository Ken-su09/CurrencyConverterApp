package com.suonk.currencyconverterapp.repositories

import com.suonk.currencyconverterapp.api.CurrencyConverterApiService
import com.suonk.currencyconverterapp.api.CurrencyConverterApiService.Companion.API_KEY
import com.suonk.currencyconverterapp.models.data.CurrencyResponse
import retrofit2.Response
import javax.inject.Inject

class CurrencyConverterRepository @Inject constructor(private val api: CurrencyConverterApiService) :
    DefaultMainRepository {

    override suspend fun getLatestRates(symbols: String): Response<CurrencyResponse> =
        api.getLatestRates(API_KEY, symbols)
}