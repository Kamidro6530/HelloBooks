package com.example.hellobooks.remote

import com.example.hellobooks.remote.dto.Response


interface BooksService {

    suspend fun getBooks(query_parameter: String) : Response

}