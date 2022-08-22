package com.example.hellobooks.remote.dto

data class Epub(
    val acsTokenLink: String,
    val downloadLink: String,
    val isAvailable: Boolean
)