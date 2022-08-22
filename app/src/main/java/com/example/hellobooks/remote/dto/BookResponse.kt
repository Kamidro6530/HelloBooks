package com.example.hellobooks.remote.dto

data class BookResponse(
    val id : String,
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val categories: List<String>,
    val pageCount: Int,
    val description: String,
    val publisher: String,
    val language: String,
    val contentVersion: String,
    val subtitle: String,
    val imageLinks: ImageLinks,
)