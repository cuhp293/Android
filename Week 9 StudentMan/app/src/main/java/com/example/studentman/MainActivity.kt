package com.example.studentman

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter(
            students,
            onEditClick = { student, position -> showEditDialog(student, position) },
            onDeleteClick = { student, position -> showDeleteDialog(student, position) }
        )

        findViewById<RecyclerView>(R.id.recycler_view_students).apply {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupAddButton() {
        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_student, null)

        AlertDialog.Builder(this)
            .setTitle("Add New Student")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val nameEdit = dialogView.findViewById<EditText>(R.id.edit_student_name)
                val idEdit = dialogView.findViewById<EditText>(R.id.edit_student_id)

                if (nameEdit.text.isNotEmpty() && idEdit.text.isNotEmpty()) {
                    val newStudent = StudentModel(
                        nameEdit.text.toString(),
                        idEdit.text.toString()
                    )
                    studentAdapter.addStudent(newStudent)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDialog(student: StudentModel, position: Int) {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_student, null)

        dialogView.findViewById<EditText>(R.id.edit_student_name).setText(student.studentName)
        dialogView.findViewById<EditText>(R.id.edit_student_id).setText(student.studentId)

        AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogView)
            .setPositiveButton("Update") { dialog, _ ->
                val nameEdit = dialogView.findViewById<EditText>(R.id.edit_student_name)
                val idEdit = dialogView.findViewById<EditText>(R.id.edit_student_id)

                if (nameEdit.text.isNotEmpty() && idEdit.text.isNotEmpty()) {
                    val updatedStudent = StudentModel(
                        nameEdit.text.toString(),
                        idEdit.text.toString()
                    )
                    studentAdapter.updateStudent(updatedStudent, position)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteDialog(student: StudentModel, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete ${student.studentName}?")
            .setPositiveButton("Delete") { _, _ ->
                val removedStudent = studentAdapter.removeStudent(position)
                showUndoDialog(removedStudent, position)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showUndoDialog(removedStudent: StudentModel, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Student Deleted")
            .setMessage("${removedStudent.studentName} has been deleted")
            .setPositiveButton("Undo") { _, _ ->
                studentAdapter.undoRemove(removedStudent, position)
            }
            .setNegativeButton("OK", null)
            .create()

        dialog.show()

        // Tự động đóng dialog sau 3 giây nếu người dùng không tương tác
        dialog.window?.decorView?.postDelayed({
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }, 3000)
    }
}