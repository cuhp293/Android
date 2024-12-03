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

class EditStudentFragment : Fragment() {
    private val args: EditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val nameEdit: EditText = view.findViewById(R.id.edit_student_name)
        val idEdit: EditText = view.findViewById(R.id.edit_student_id)
        val btnSave: Button = view.findViewById(R.id.btn_save)
        val btnCancel: Button = view.findViewById(R.id.btn_cancel)

        // Hiển thị thông tin sinh viên được chọn
        val student = args.student
        val position = args.position
        nameEdit.setText(student.studentName)
        idEdit.setText(student.studentId)

        btnSave.setOnClickListener {
            val name = nameEdit.text.toString().trim()
            val id = idEdit.text.toString().trim()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                // Lưu sinh viên đã chỉnh sửa
                val updatedStudent = StudentModel(name, id)

                // Truyền sinh viên đã sửa và vị trí về StudentListFragment
                findNavController().previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("updated_student", Pair(position, updatedStudent))

                // Quay về màn hình danh sách
                findNavController().navigateUp()
            } else {
                if (name.isEmpty()) nameEdit.error = "Please enter StudentName"
                if (id.isEmpty()) idEdit.error = "Please enter StudentID"
            }
        }

        btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}