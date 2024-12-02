package com.example.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val btnSave: Button = findViewById(R.id.btn_save)
        val btnCancel: Button = findViewById(R.id.btn_cancel)
        val editName: EditText = findViewById(R.id.edit_student_name)
        val editId: EditText = findViewById(R.id.edit_student_id)

        btnSave.setOnClickListener {
            val name = editName.text.toString().trim()
            val id = editId.text.toString().trim()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                // Tạo Intent để trả về MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("STUDENT_NAME", name)
                resultIntent.putExtra("STUDENT_ID", id)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                if (name.isEmpty()) editName.error = "Vui lòng nhập tên"
                if (id.isEmpty()) editId.error = "Vui lòng nhập mã sinh viên"
            }
        }

        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}