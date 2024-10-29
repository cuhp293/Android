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

class MainActivity : AppCompatActivity() {

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.72,
        "JPY" to 110.15,
        "VND" to 23200.0
    )

    private var isSourceEditing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val convertedAmountEditText = findViewById<EditText>(R.id.convertedAmountEditText)
        val sourceCurrencySpinner = findViewById<Spinner>(R.id.sourceCurrencySpinner)
        val targetCurrencySpinner = findViewById<Spinner>(R.id.targetCurrencySpinner)

        // Set up source currency spinner
        val sourceCurrencies = exchangeRates.keys.toList()
        val sourceCurrencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sourceCurrencies)
        sourceCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrencySpinner.adapter = sourceCurrencyAdapter

        // Set up target currency spinner
        val targetCurrencies = exchangeRates.keys.toList()
        val targetCurrencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, targetCurrencies)
        targetCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        targetCurrencySpinner.adapter = targetCurrencyAdapter

        // Set up text change listeners
        amountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateConvertedAmount(amountEditText, convertedAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        amountEditText.setOnClickListener {
            isSourceEditing = true
            updateConvertedAmount(amountEditText, convertedAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
        }

        convertedAmountEditText.setOnClickListener {
            isSourceEditing = false
            updateConvertedAmount(amountEditText, convertedAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
        }

        sourceCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSourceEditing) {
                    updateConvertedAmount(amountEditText, convertedAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        targetCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!isSourceEditing) {
                    updateConvertedAmount(amountEditText, convertedAmountEditText, sourceCurrencySpinner, targetCurrencySpinner)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateConvertedAmount(
        amountEditText: EditText,
        convertedAmountEditText: EditText,
        sourceCurrencySpinner: Spinner,
        targetCurrencySpinner: Spinner
    ) {
        val amount = amountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val sourceCurrency = sourceCurrencySpinner.selectedItem.toString()
        val targetCurrency = targetCurrencySpinner.selectedItem.toString()

        val sourceRate = exchangeRates[sourceCurrency] ?: 1.0
        val targetRate = exchangeRates[targetCurrency] ?: 1.0

        val convertedAmount = (amount * (targetRate / sourceRate)).toDouble()
        if (isSourceEditing) {
            convertedAmountEditText.setText(convertedAmount.toString())
        } else {
            amountEditText.setText(convertedAmount.toString())
        }
    }
}