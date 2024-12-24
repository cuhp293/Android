package com.example.studentmanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE studentId = :id")
    suspend fun getStudentById(id: String): Student?

    @Query("SELECT * FROM students WHERE full_name LIKE '%' || :query || '%' OR studentId LIKE '%' || :query || '%'")
    fun searchStudents(query: String): Flow<List<Student>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Delete
    suspend fun deleteStudents(students: List<Student>)
}