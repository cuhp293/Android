package com.example.calculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private var firstNumber: Int = 0
    private var operation: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val buttonIds = listOf(
            R.id.btnCE, R.id.btnC, R.id.btnBS, R.id.btnDivide,
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnMultiply,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btnMinus,
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btnPlus,
            R.id.btnOpposite, R.id.btn0, R.id.btnDot, R.id.btnEqual
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }
    }

    private fun onButtonClick(button: Button) {
        when (button.text.toString()) {
            in "0".."9" -> appendNumber(button.text.toString())
            "+", "-", "*", "/" -> setOperation(button.text.toString())
            "=" -> calculateResult()
            "C" -> clearResult()
            "CE" -> clearEntry()
            "BS" -> backspace()
            "+/-" -> changeSign()
        }
    }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            tvResult.text = number
            isNewOperation = false
        } else {
            tvResult.append(number)
        }
    }

    private fun setOperation(op: String) {
        firstNumber = tvResult.text.toString().toInt()
        operation = op
        isNewOperation = true
    }

    private fun calculateResult() {
        val secondNumber = tvResult.text.toString().toInt()
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
            else -> secondNumber
        }
        tvResult.text = result.toString()
        isNewOperation = true
    }

    private fun clearResult() {
        tvResult.text = "0"
        firstNumber = 0
        operation = ""
        isNewOperation = true
    }

    private fun clearEntry() {
        tvResult.text = "0"
        isNewOperation = true
    }

    private fun backspace() {
        val currentText = tvResult.text.toString()
        if (currentText.length > 1) {
            tvResult.text = currentText.substring(0, currentText.length - 1)
        } else {
            tvResult.text = "0"
            isNewOperation = true
        }
    }

    private fun changeSign() {
        val currentText = tvResult.text.toString().toInt()
        tvResult.text = (-currentText).toString()
    }
}