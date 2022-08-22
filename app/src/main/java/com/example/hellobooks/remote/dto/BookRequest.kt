package com.example.hellobooks.remote.dto

data class BookRequest (
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val subtitle: String,
)