package com.example.studentmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.databinding.ItemStudentBinding

class StudentAdapter(
    private val onItemClick: (Student) -> Unit,
    private val onCheckboxChanged: (Student, Boolean) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    private val selectedStudents = mutableSetOf<Student>()

    inner class StudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.apply {
                tvStudentId.text = "MSSV: ${student.studentId}"
                tvFullName.text = student.fullName
                checkbox.isChecked = selectedStudents.contains(student)

                root.setOnClickListener { onItemClick(student) }
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    onCheckboxChanged(student, isChecked)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun toggleSelection(student: Student, isSelected: Boolean) {
        if (isSelected) {
            selectedStudents.add(student)
        } else {
            selectedStudents.remove(student)
        }
    }

    fun getSelectedStudents() = selectedStudents.toList()
}
