package com.bikcode.booksapp.domain.model

data class User(
    val uid: String,
    val name: String,
    val email: String,
    val password: String,
    val image: String
)
