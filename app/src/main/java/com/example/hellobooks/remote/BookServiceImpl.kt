package com.example.hellobooks.remote

import com.example.hellobooks.constants.Constants
import com.example.hellobooks.constants.HttpRoutes
import com.example.hellobooks.remote.dto.BookResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class BookServiceImpl(private val client : HttpClient) : BooksService {
    override suspend fun getBook(): List<BookResponse> {
      return client.get{ url(HttpRoutes.TEST_URL)}.body()

}
}