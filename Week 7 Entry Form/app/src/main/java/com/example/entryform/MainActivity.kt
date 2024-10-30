package com.example.entryform

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var edtStudentId: TextInputEditText
    private lateinit var edtFullName: TextInputEditText
    private lateinit var rgGender: RadioGroup
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPhone: TextInputEditText
    private lateinit var btnShowCalendar: Button
    private lateinit var tvBirthday: TextView
    private lateinit var calendarView: CalendarView
    private lateinit var spnProvince: Spinner
    private lateinit var spnDistrict: Spinner
    private lateinit var spnWard: Spinner
    private lateinit var cbSports: CheckBox
    private lateinit var cbMovies: CheckBox
    private lateinit var cbMusic: CheckBox
    private lateinit var cbAgreement: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var addressHelper: AddressHelper

    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupCalendar()
        setupSpinners()
        setupSubmitButton()
    }

    private fun initViews() {
        edtStudentId = findViewById(R.id.edtStudentId)
        edtFullName = findViewById(R.id.edtFullName)
        rgGender = findViewById(R.id.rgGender)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnShowCalendar = findViewById(R.id.btnShowCalendar)
        tvBirthday = findViewById(R.id.tvBirthday)
        calendarView = findViewById(R.id.calendarView)
        spnProvince = findViewById(R.id.spnProvince)
        spnDistrict = findViewById(R.id.spnDistrict)
        spnWard = findViewById(R.id.spnWard)
        cbSports = findViewById(R.id.cbSports)
        cbMovies = findViewById(R.id.cbMovies)
        cbMusic = findViewById(R.id.cbMusic)
        cbAgreement = findViewById(R.id.cbAgreement)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun setupCalendar() {
        btnShowCalendar.setOnClickListener {
            if (calendarView.visibility == View.VISIBLE) {
                calendarView.visibility = View.GONE
            } else {
                calendarView.visibility = View.VISIBLE
            }
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvBirthday.text = "Ngày sinh: $selectedDate"
            calendarView.visibility = View.GONE
        }
    }

    private fun setupSpinners() {
        // Dữ liệu mẫu cho các spinner
        val provinces = arrayOf("Hà Nội", "TP.HCM", "Đà Nẵng")
        val districts = arrayOf("Quận 1", "Quận 2", "Quận 3")
        val wards = arrayOf("Phường 1", "Phường 2", "Phường 3")

        spnProvince.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provinces)
        spnDistrict.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, districts)
        spnWard.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wards)
    }

    private fun setupSubmitButton() {
        btnSubmit.setOnClickListener {
            if (validateForm()) {
                // Xử lý khi form hợp lệ
                Toast.makeText(this, "Form đã được gửi thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Kiểm tra họ tên
        if (edtFullName.text.isNullOrEmpty()) {
            edtFullName.error = "Vui lòng nhập họ tên"
            isValid = false
        }

        // Kiểm tra MSSV
        if (edtStudentId.text.isNullOrEmpty()) {
            edtStudentId.error = "Vui lòng nhập MSSV"
            isValid = false
        }

        // Kiểm tra giới tính
        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra email
        if (edtEmail.text.isNullOrEmpty()) {
            edtEmail.error = "Vui lòng nhập email"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()) {
            edtEmail.error = "Email không hợp lệ"
            isValid = false
        }

        // Kiểm tra số điện thoại
        if (edtPhone.text.isNullOrEmpty()) {
            edtPhone.error = "Vui lòng nhập số điện thoại"
            isValid = false
        } else if (!android.util.Patterns.PHONE.matcher(edtPhone.text.toString()).matches()) {
            edtPhone.error = "Số điện thoại không hợp lệ"
            isValid = false
        }

        // Kiểm tra ngày sinh
        if (selectedDate == null) {
            Toast.makeText(this, "Vui lòng chọn ngày sinh", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra sở thích
        if (!cbSports.isChecked && !cbMovies.isChecked && !cbMusic.isChecked) {
            Toast.makeText(this, "Vui lòng chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra đồng ý điều khoản
        if (!cbAgreement.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với các điều khoản", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }
}