package com.example.hellobooks.di

import androidx.annotation.NonNull
import com.example.hellobooks.constants.HttpRoutes
import com.example.hellobooks.remote.BookServiceImpl
import com.example.hellobooks.remote.BooksService
import com.example.hellobooks.remote.dto.BookResponse
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import javax.inject.Singleton
import kotlin.text.get

@Module
@InstallIn(SingletonComponent::class)
object RemoteBookModule {

    @Singleton
    @Provides
    fun provideHttpClient() : HttpClient {
        return  HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    suspend fun provideBooksService(client: HttpClient) : List<BookResponse> {
        return BookServiceImpl(client).getBook()
    }
}