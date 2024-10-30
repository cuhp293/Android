package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editSearch = findViewById(R.id.editSearch)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter

        // Data
        val sampleStudents = listOf(
            Student("Đinh Thị Hồng Phúc", "20215118"),
            Student("Nguyễn Văn A", "20210001"),
            Student("Trần Thị B", "20210002"),
            Student("Lê Văn C", "20210003"),
            Student("Phạm Thị D", "20210004"),
            Student("Hoàng Văn E", "20210005"),
            Student("Đỗ Thị F", "20210006"),
            Student("Ngô Thị G", "20220007"),
            Student("Bùi Văn H", "20220008"),
            Student("Trịnh Thị I", "20220009"),
            Student("Đặng Văn J", "20230010"),
            Student("Vũ Thị K", "20230011"),
            Student("Phan Văn L", "20230012"),
            Student("Lý Thị M", "20220013"),
            Student("Tạ Văn N", "20220014"),
            Student("Nguyễn Thị O", "20210015"),
            Student("Trương Văn P", "20230016")
        )
        adapter.setStudents(sampleStudents)

        // Search
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}