package com.example.searchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students = listOf<Student>()
    private var filteredStudents = listOf<Student>()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvStudentId: TextView = itemView.findViewById(R.id.tvStudentId)

        fun bind(student: Student) {
            tvName.text = student.name
            tvStudentId.text = student.studentId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(filteredStudents[position])
    }

    override fun getItemCount(): Int = filteredStudents.size

    fun setStudents(newStudents: List<Student>) {
        students = newStudents
        filteredStudents = newStudents
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredStudents = if (query.length <= 2) {
            students
        } else {
            students.filter { student ->
                student.name.contains(query, ignoreCase = true) ||
                        student.studentId.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}