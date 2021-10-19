package com.suonk.currencyconverterapp.di

import com.suonk.currencyconverterapp.api.CurrencyConverterApiService
import com.suonk.currencyconverterapp.api.CurrencyConverterApiService.Companion.BASE_URL
import com.suonk.currencyconverterapp.repositories.CurrencyConverterRepository
import com.suonk.currencyconverterapp.repositories.DefaultMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCurrencyConverterApiService(retrofit: Retrofit): CurrencyConverterApiService =
        retrofit.create(CurrencyConverterApiService::class.java)

    @Provides
    fun provideDefaultMainRepository(api: CurrencyConverterApiService): DefaultMainRepository =
        CurrencyConverterRepository(api)
}