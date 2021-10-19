package com.suonk.currencyconverterapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.currencyconverterapp.models.data.CurrencyResponse
import com.suonk.currencyconverterapp.repositories.DefaultMainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val repository: DefaultMainRepository) :
    ViewModel() {

    val currencyResponseLiveData = MutableLiveData<CurrencyResponse>()

    fun getLatestRates(symbols: String) = viewModelScope.launch {
        val response = repository.getLatestRates(symbols)

        if (response.isSuccessful) {
            currencyResponseLiveData.postValue(response.body())
        }
    }
}