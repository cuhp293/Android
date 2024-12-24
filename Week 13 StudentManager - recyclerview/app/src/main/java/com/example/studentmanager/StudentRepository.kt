package com.example.studentmanager

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents = studentDao.getAllStudents()

    fun searchStudents(query: String) = studentDao.searchStudents(query)

    suspend fun insert(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun update(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
    }

    suspend fun deleteMultiple(students: List<Student>) {
        studentDao.deleteStudents(students)
    }

    suspend fun getStudentById(id: String): Student? {
        return studentDao.getStudentById(id)
    }
}