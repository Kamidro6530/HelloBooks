package com.example.hellobooks.remote

import com.example.hellobooks.BuildConfig
import com.example.hellobooks.constants.HttpRoutes
import com.example.hellobooks.remote.dto.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class BookServiceImpl @Inject constructor(private val client: HttpClient) : BooksService {

    override suspend fun getBooksFromBooksService(query_parameter: String): Response = client.request { url(HttpRoutes.BASE_URL+query_parameter+":keyes&key="+BuildConfig.API_KEY) }.body()


}

