package com.suonk.currencyconverterapp.di

import com.suonk.currencyconverterapp.api.CurrencyConverterApiService
import com.suonk.currencyconverterapp.api.CurrencyConverterApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCurrencyConverterApiService(retrofit: Retrofit): CurrencyConverterApiService =
        retrofit.create(CurrencyConverterApiService::class.java)
}