package com.example.studentmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val dao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(dao)
        allStudents = repository.allStudents.asLiveData()
    }

    private val _searchResults = MutableLiveData<List<Student>>()
    val searchResults: LiveData<List<Student>> = _searchResults

    fun searchStudents(query: String) {
        viewModelScope.launch {
            repository.searchStudents(query).collect {
                _searchResults.value = it
            }
        }
    }

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }

    fun deleteSelected(students: List<Student>) = viewModelScope.launch {
        repository.deleteMultiple(students)
    }

    fun getStudentById(id: String): LiveData<Student?> = liveData {
        emit(repository.getStudentById(id))
    }
}