package com.example.email

data class Email(
    val initials: String,
    val emailName: String,
    val emailContent: String,
    val emailTime: String,
    val isStarred: Boolean
)