package com.example.hellobooks.remote

import com.example.hellobooks.remote.dto.Response


interface BooksService {

    suspend fun getBooksFromBooksService(query_parameter: String) : Response

}