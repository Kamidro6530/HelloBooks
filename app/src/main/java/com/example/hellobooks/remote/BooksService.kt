package com.example.hellobooks.remote

import com.example.hellobooks.remote.dto.BookResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType.Application.Json

interface BooksService {

    suspend fun getBook() : List<BookResponse>


    companion object {
        fun create(): BooksService {
            return BookServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }


                }
            )
        }
    }
}