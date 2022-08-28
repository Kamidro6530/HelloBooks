package com.example.hellobooks.remote

import com.example.hellobooks.remote.dto.BookResponse
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType.Application.Json
import javax.inject.Singleton


interface BooksService {


    suspend fun getBook() : List<BookResponse>

}