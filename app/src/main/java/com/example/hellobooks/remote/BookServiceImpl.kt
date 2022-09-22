package com.example.hellobooks.remote

import com.example.hellobooks.constants.HttpRoutes
import com.example.hellobooks.remote.dto.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javax.inject.Inject

class BookServiceImpl @Inject constructor(private val client: HttpClient) : BooksService {


    override suspend fun getBooks(): Response = client.request { url(HttpRoutes.TEST_URL) }.body()


}

