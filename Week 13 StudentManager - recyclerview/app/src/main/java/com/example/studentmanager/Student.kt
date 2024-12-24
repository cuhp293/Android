package com.example.studentmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey val studentId: String, // mssv
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "birth_date") val birthDate: String,
    @ColumnInfo(name = "email") val email: String
)
