package com.suonk.currencyconverterapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.suonk.currencyconverterapp.databinding.ActivityMainBinding
import com.suonk.currencyconverterapp.models.data.Rates
import com.suonk.currencyconverterapp.viewmodels.CurrencyConverterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CurrencyConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    private fun initializeUI() {
        binding.apply {
            converterButton.setOnClickListener {
                if (converterEditText.text?.isNotEmpty()!!) {
                    getLatestRatesFromViewModel(currencySpinnerTo.selectedItem.toString())
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Fields should not be empty",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }

    private fun getLatestRatesFromViewModel(symbols: String) {
        Log.i("getLatestRates", symbols)
        viewModel.getLatestRates(symbols)

        viewModel.currencyResponseLiveData.observe(this, { currencyResponse ->
            val allRates = mutableListOf<Rates>()
            allRates.add(currencyResponse.rates)
            getSymbols(symbols, currencyResponse.rates)
            Log.i("getLatestRates", "$symbols : ${getSymbols(symbols, currencyResponse.rates)}")
            binding.apply {
                val firstDouble = converterEditText.text?.toString()?.toDouble()!!
                val result = (firstDouble * getSymbols(symbols, currencyResponse.rates))

                converterResult.text = "${firstDouble.toInt()} EUR = $result $symbols"
            }
            // 10 EUR = 15.0 USD
        })
    }

    private fun getSymbols(symbols: String, rates: Rates): Double {
        val ratesMap = mapOf(
            "AED" to rates.AED,
            "AFN" to rates.AFN,
            "ALL" to rates.ALL,
            "AMD" to rates.AMD,
            "ANG" to rates.ANG,
            "AZN" to rates.AZN,
            "BAM" to rates.BAM,
            "BBD" to rates.BBD,
            "BDT" to rates.BDT,
            "BTN" to rates.BTN,
            "BYR" to rates.BYR,
            "BZD" to rates.BZD,
            "CAD" to rates.CAD,
            "CDF" to rates.CDF,
            "CHF" to rates.CHF,
            "CLF" to rates.CLF,
            "CLP" to rates.CLP,
            "CNY" to rates.CNY,
            "COP" to rates.COP,
            "CRC" to rates.CRC,
            "CZK" to rates.CZK,
            "DJF" to rates.DJF,
            "DKK" to rates.DKK,
            "DOP" to rates.DOP,
            "DZD" to rates.DZD,
            "EGP" to rates.EGP,
            "ERN" to rates.ERN,
            "ETB" to rates.ETB,
            "FJD" to rates.FJD,
            "FKP" to rates.FKP,
            "GBP" to rates.GBP,
            "GEL" to rates.GEL,
            "HKD" to rates.HKD,
            "HNL" to rates.HNL,
            "HTG" to rates.HTG,
            "HUF" to rates.HUF,
            "IDR" to rates.IDR,
            "IQD" to rates.IQD,
            "IRR" to rates.IRR,
            "ISK" to rates.ISK,
            "JEP" to rates.JEP,
            "JMD" to rates.JMD,
            "JOD" to rates.JOD,
            "JPY" to rates.JPY,
            "KES" to rates.KES,
            "KHR" to rates.KHR,
            "KYD" to rates.KYD,
            "KZT" to rates.KZT,
            "LAK" to rates.LAK,
            "LBP" to rates.LBP,
            "LKR" to rates.LKR,
            "LYD" to rates.LYD,
            "MAD" to rates.MAD,
            "MDL" to rates.MDL,
            "MGA" to rates.MGA,
            "MKD" to rates.MKD,
            "MMK" to rates.MMK,
            "NAD" to rates.NAD,
            "NPR" to rates.NPR,
            "NZD" to rates.NZD,
            "OMR" to rates.OMR,
            "PAB" to rates.PAB,
            "PEN" to rates.PEN,
            "QAR" to rates.QAR,
            "RON" to rates.RON,
            "RSD" to rates.RSD,
            "RUB" to rates.RUB,
            "RWF" to rates.RWF,
            "SAR" to rates.SAR,
            "SBD" to rates.SBD,
            "SCR" to rates.SCR,
            "SDG" to rates.SDG,
            "TJS" to rates.TJS,
            "TMT" to rates.TMT,
            "TOP" to rates.TOP,
            "TZS" to rates.TZS,
            "UAH" to rates.UAH,
            "UGX" to rates.UGX,
            "USD" to rates.USD,
            "UYU" to rates.UYU,
            "UZS" to rates.UZS,
            "VEF" to rates.VEF,
            "VND" to rates.VND,
            "VUV" to rates.VUV,
            "WST" to rates.WST,
            "XAF" to rates.XAF,
            "XOF" to rates.XOF,
            "XPF" to rates.XPF,
            "YER" to rates.YER,
            "ZAR" to rates.ZAR,
            "ZMW" to rates.ZMW
        )

        return ratesMap[symbols]!!
    }
}