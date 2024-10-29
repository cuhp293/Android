package com.example.cal_currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var etSourceAmount: EditText
    private lateinit var spinnerSourceCurrency: Spinner
    private lateinit var etTargetAmount: EditText
    private lateinit var spinnerTargetCurrency: Spinner
    private lateinit var tvExchangeRate: TextView

    private val currencyRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPY" to 134.7,
        "GBP" to 0.8,
        "AUD" to 1.5,
        "CAD" to 1.3,
        "CHF" to 0.9,
        "CNY" to 6.9,
        "HKD" to 7.8,
        "NZD" to 1.6,
        "VND" to 23000.0
    )

    private var sourceCurrency = "USD"
    private var targetCurrency = "USD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSourceAmount = findViewById(R.id.etSourceAmount)
        spinnerSourceCurrency = findViewById(R.id.spinnerSourceCurrency)
        etTargetAmount = findViewById(R.id.etTargetAmount)
        spinnerTargetCurrency = findViewById(R.id.spinnerTargetCurrency)
        tvExchangeRate = findViewById(R.id.tvExchangeRate)

        setupCurrencySpinners()
        setupEditTextListeners()
    }

    private fun setupCurrencySpinners() {
        val currencies = currencyRates.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSourceCurrency.adapter = adapter
        spinnerTargetCurrency.adapter = adapter

        spinnerSourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                sourceCurrency = currencies[position]
                updateExchangeRate()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerTargetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                targetCurrency = currencies[position]
                updateExchangeRate()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupEditTextListeners() {
        etSourceAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateExchangeRate()
            }
        })

        etSourceAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                spinnerSourceCurrency.performClick()
            }
        }

        etTargetAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                spinnerTargetCurrency.performClick()
            }
        }
    }

    private fun updateExchangeRate() {
        val sourceAmount = etSourceAmount.text.toString().toDoubleOrNull() ?: 0.0
        val sourceRate = currencyRates[sourceCurrency] ?: 1.0
        val targetRate = currencyRates[targetCurrency] ?: 1.0

        val targetAmount = sourceAmount * (targetRate / sourceRate)
        etTargetAmount.setText(targetAmount.toString())

        val exchangeRateText = "1 $sourceCurrency = ${targetRate / sourceRate} $targetCurrency"
        tvExchangeRate.text = exchangeRateText
    }
}