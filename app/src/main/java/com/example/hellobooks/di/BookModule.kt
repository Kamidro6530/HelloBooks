package com.example.hellobooks.di

import android.content.Context
import androidx.room.Room
import com.example.hellobooks.repository.BookRepository
import com.example.hellobooks.room.BookDao
import com.example.hellobooks.room.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookModule {

    @Singleton
    @Provides
    fun provideBookDatabase(@ApplicationContext appContext: Context): BookDatabase {
        return Room.databaseBuilder(appContext, BookDatabase::class.java, "book_database").build()
    }

    @Provides
    fun provideBookDao(database: BookDatabase) : BookDao{
        return database.bookDao()
    }
    @Singleton
    @Provides
    fun provideBookRepository(bookDao: BookDao) : BookRepository {
        return BookRepository(bookDao)
    }
}