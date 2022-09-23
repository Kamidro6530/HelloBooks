package com.example.hellobooks.remote

import com.example.hellobooks.remote.dto.Response
import okhttp3.Call


interface BooksService {

    suspend fun getBooks(query_parameter: String) : Response

}