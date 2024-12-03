package com.example.studentmanger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class AddStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val btnSave: Button = view.findViewById(R.id.btn_save)
        val btnCancel: Button = view.findViewById(R.id.btn_cancel)
        val editName: EditText = view.findViewById(R.id.edit_student_name)
        val editId: EditText = view.findViewById(R.id.edit_student_id)

        btnSave.setOnClickListener {
            val name = editName.text.toString().trim()
            val id = editId.text.toString().trim()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                // Lưu sinh viên mới vào danh sách toàn cục
                val newStudent = StudentModel(name, id)

                // Truy xuất StudentListFragment để thêm sinh viên
                val navController = findNavController()
                val studentListFragment = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("new_student", newStudent)

                // Quay về màn hình danh sách
                navController.navigateUp()
            } else {
                if (name.isEmpty()) editName.error = "Please enter StudentName"
                if (id.isEmpty()) editId.error = "Please enter StudentID"
            }
        }

        btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}