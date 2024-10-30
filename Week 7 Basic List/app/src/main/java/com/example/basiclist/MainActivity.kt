package com.example.basiclist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var et_input: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btn_show: Button
    private lateinit var listView: ListView
    private lateinit var tv_error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo views
        et_input = findViewById(R.id.et_input)
        radioGroup = findViewById(R.id.radioGroup)
        btn_show = findViewById(R.id.btn_show)
        listView = findViewById(R.id.listView)
        tv_error = findViewById(R.id.tv_error)

        btn_show.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        tv_error.text = ""
        val input = et_input.text.toString()

        if (input.isEmpty()) {
            tv_error.text = "Vui lòng nhập số!"
            return
        }

        try {
            val n = input.toInt()
            if (n < 0) {
                tv_error.text = "Vui lòng nhập số nguyên dương!"
                return
            }

            val numbers = when (radioGroup.checkedRadioButtonId) {
                R.id.rb_even -> getEvenNumbers(n)
                R.id.rb_odd -> getOddNumbers(n)
                R.id.rb_square -> getSquareNumbers(n)
                else -> {
                    tv_error.text = "Vui lòng chọn loại số!"
                    return
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter

        } catch (e: NumberFormatException) {
            tv_error.text = "Dữ liệu không hợp lệ!"
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { num ->
            val sqrt = sqrt(num.toDouble()).toInt()
            sqrt * sqrt == num
        }
    }
}