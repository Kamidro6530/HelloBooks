package com.example.hellobooks.remote

import com.example.hellobooks.constants.HttpRoutes
import com.example.hellobooks.remote.dto.BookResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject
import kotlin.text.get

class BookServiceImpl @Inject constructor(private val client : HttpClient) : BooksService {
    override suspend fun getBook(): List<BookResponse> {
        return client.get{ url(HttpRoutes.TEST_URL)}.body()

    }
}