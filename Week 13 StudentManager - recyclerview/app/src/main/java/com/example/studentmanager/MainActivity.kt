package com.example.studentmanager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        setupRecyclerView()
        setupSearch()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter(
            onItemClick = { student ->
                val intent = Intent(this, StudentDetailActivity::class.java)
                intent.putExtra("student_id", student.studentId)
                startActivity(intent)
            },
            onCheckboxChanged = { student, isChecked ->
                adapter.toggleSelection(student, isChecked)
            }
        )

        binding.rvStudents.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener { text ->
            viewModel.searchStudents(text.toString())
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            viewModel.searchStudents(query)
        }
    }

    private fun setupObservers() {
        viewModel.allStudents.observe(this) { students ->
            adapter.submitList(students)
        }

        viewModel.searchResults.observe(this) { students ->
            adapter.submitList(students)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this, AddStudentActivity::class.java))
                true
            }
            R.id.action_delete_selected -> {
                deleteSelectedStudents()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteSelectedStudents() {
        val selectedStudents = adapter.getSelectedStudents()
        if (selectedStudents.isNotEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa ${selectedStudents.size} sinh viên đã chọn?")
                .setPositiveButton("Xóa") { _, _ ->
                    viewModel.deleteSelected(selectedStudents)
                }
                .setNegativeButton("Hủy", null)
                .show()
        } else {
            Toast.makeText(this, "Vui lòng chọn sinh viên cần xóa", Toast.LENGTH_SHORT).show()
        }
    }
}