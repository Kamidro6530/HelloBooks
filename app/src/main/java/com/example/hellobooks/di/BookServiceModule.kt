package com.example.hellobooks.di

import com.example.hellobooks.remote.BookServiceImpl
import com.example.hellobooks.remote.BooksService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookServiceModule {

    @Singleton
    @Binds
    abstract fun bindBookService(bookServiceImpl: BookServiceImpl) : BooksService

}