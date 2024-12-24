package com.example.studentmanager

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanager.databinding.ActivityStudentDetailBinding

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailBinding
    private lateinit var viewModel: StudentViewModel
    private var currentStudent: Student? = null // Thêm biến để lưu student hiện tại

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        setupViews()
        loadStudentDetails()
    }

    private fun setupViews() {
        binding.btnUpdate.setOnClickListener {
            updateStudent()
        }

        binding.btnDelete.setOnClickListener {
            deleteStudent()
        }
    }

    private fun loadStudentDetails() {
        val studentId = intent.getStringExtra("student_id")
        studentId?.let { id ->
            viewModel.getStudentById(id).observe(this) { student ->
                student?.let {
                    currentStudent = it // Lưu student hiện tại
                    binding.apply {
                        etStudentId.setText(it.studentId)
                        etFullName.setText(it.fullName)
                        etBirthDate.setText(it.birthDate)
                        etEmail.setText(it.email)
                    }
                }
            }
        }
    }

    private fun deleteStudent() {
        currentStudent?.let { student ->
            AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa sinh viên này?")
                .setPositiveButton("Xóa") { _, _ ->
                    viewModel.delete(student)
                    finish() // Đóng activity sau khi xóa
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }

    private fun updateStudent() {
        val updatedStudent = Student(
            studentId = binding.etStudentId.text.toString(),
            fullName = binding.etFullName.text.toString(),
            birthDate = binding.etBirthDate.text.toString(),
            email = binding.etEmail.text.toString()
        )
        viewModel.update(updatedStudent)
        finish()
    }
}