package com.example.hellobooks.remote.dto

data class BookRequest(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val categories: List<String>,
    val pageCount: Int,
    //ISBN
    val description: String,
    ///Other options
    val publisher: String,
    val language: String,
    val contentVersion: String,
    val subtitle: String,
    ///Image
    val imageLinks: ImageLinks,
)
