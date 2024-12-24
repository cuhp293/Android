package com.example.studentmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanager.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        binding.btnSave.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val student = Student(
            studentId = binding.etStudentId.text.toString(),
            fullName = binding.etFullName.text.toString(),
            birthDate = binding.etBirthDate.text.toString(),
            email = binding.etEmail.text.toString()
        )

        if (validateInput(student)) {
            viewModel.insert(student)
            finish()
        }
    }

    private fun validateInput(student: Student): Boolean {
        if (student.studentId.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền MSSV", Toast.LENGTH_SHORT).show()
            return false
        } else if (student.fullName.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền tên sinh viên", Toast.LENGTH_SHORT).show()
            return false
        } else if (student.birthDate.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền ngày sinh của sinh viên", Toast.LENGTH_SHORT).show()
            return false
        } else if (student.email.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền email sinh viên", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
