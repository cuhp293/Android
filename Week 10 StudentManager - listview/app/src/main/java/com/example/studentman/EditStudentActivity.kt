package com.example.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val nameEdit: EditText = findViewById(R.id.edit_student_name)
        val idEdit: EditText = findViewById(R.id.edit_student_id)
        val btnSave: Button = findViewById(R.id.btn_save)

        // Get the student to edit from intent
        val student = intent.getParcelableExtra<StudentModel>("STUDENT")
        position = intent.getIntExtra("POSITION", -1)

        // Populate existing student details
        student?.let {
            nameEdit.setText(it.studentName)
            idEdit.setText(it.studentId)
        }

        btnSave.setOnClickListener {
            val name = nameEdit.text.toString().trim()
            val id = idEdit.text.toString().trim()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                val updatedStudent = StudentModel(name, id)
                val resultIntent = Intent().apply {
                    putExtra("UPDATED_STUDENT", updatedStudent)
                    putExtra("POSITION", position)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Optional: Show error if fields are empty
                if (name.isEmpty()) nameEdit.error = "Name cannot be empty"
                if (id.isEmpty()) idEdit.error = "ID cannot be empty"
            }
        }

        val btnCancel: Button = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}