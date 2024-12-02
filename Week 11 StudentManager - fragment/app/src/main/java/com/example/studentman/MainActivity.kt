package com.example.studentman

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: ArrayAdapter<StudentModel>
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

    private var selectedStudentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListView()
    }

    private fun setupListView() {
        val listView: ListView = findViewById(R.id.list_view_students)
        studentAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            students
        )
        listView.adapter = studentAdapter
        registerForContextMenu(listView)

        listView.setOnItemLongClickListener { _, _, position, _ ->
            selectedStudentPosition = position
            false // Return false to allow context menu to show
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.student_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            R.id.menu_edit -> {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("STUDENT", students[position])
                    putExtra("POSITION", position)
                }
                startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)
                true
            }
            R.id.menu_remove -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                ADD_STUDENT_REQUEST_CODE -> {
//                    val newStudent = data?.getParcelableExtra<StudentModel>("NEW_STUDENT")
//                    newStudent?.let {
//                        students.add(it)
//                        studentAdapter.notifyDataSetChanged()
//                    }
//                }
//                EDIT_STUDENT_REQUEST_CODE -> {
//                    val updatedStudent = data?.getParcelableExtra<StudentModel>("UPDATED_STUDENT")
//                    val position = data?.getIntExtra("POSITION", -1)
//                    if (updatedStudent != null && position != null && position != -1) {
//                        students[position] = updatedStudent
//                        studentAdapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        }
//    }
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK) {
        when (requestCode) {
            ADD_STUDENT_REQUEST_CODE -> {
                // Lấy dữ liệu từ Intent
                val studentName = data?.getStringExtra("STUDENT_NAME")
                val studentId = data?.getStringExtra("STUDENT_ID")

                // Tạo đối tượng StudentModel
                if (studentName != null && studentId != null) {
                    val newStudent = StudentModel(studentName, studentId)
                    students.add(newStudent)
                    studentAdapter.notifyDataSetChanged()
                }
            }
            EDIT_STUDENT_REQUEST_CODE -> {
                val updatedStudent = data?.getParcelableExtra<StudentModel>("UPDATED_STUDENT")
                val position = data?.getIntExtra("POSITION", -1)
                if (updatedStudent != null && position != null && position != -1) {
                    students[position] = updatedStudent
                    studentAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}

    companion object {
        const val ADD_STUDENT_REQUEST_CODE = 1
        const val EDIT_STUDENT_REQUEST_CODE = 2
    }
}