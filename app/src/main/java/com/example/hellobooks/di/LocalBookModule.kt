package com.example.hellobooks.di

import android.content.Context
import androidx.room.Room
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.repository.BookRepository
import com.example.hellobooks.local.room.BookDao
import com.example.hellobooks.local.room.BookDatabase
import com.example.hellobooks.remote.BookServiceImpl
import com.example.hellobooks.remote.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalBookModule {

    @Singleton
    @Provides
    fun provideBookDatabase(@ApplicationContext appContext: Context): BookDatabase {
        return Room.databaseBuilder(appContext, BookDatabase::class.java, "book_database").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBookDao(database: BookDatabase) : BookDao {
        return database.bookDao()
    }
    @Singleton
    @Provides
    fun provideBookRepository(bookDao: BookDao,booksService: BookServiceImpl) : BookRepository {
        return BookRepository(bookDao,booksService)
    }

    @Singleton
    @Provides
    fun provideConverters(): Converters {
        return Converters()
    }





}