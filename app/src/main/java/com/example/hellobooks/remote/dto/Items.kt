package com.example.hellobooks.remote.dto

data class Items(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)