package com.example.hellobooks.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.hellobooks.local.room.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestLocalBookModule {

    @Provides
    @Named("bookdb-test")
    fun provideInMemoryBookDatabase(@ApplicationContext appContext: Context) =
        Room.inMemoryDatabaseBuilder(
            appContext,
            BookDatabase::class.java
        ).allowMainThreadQueries().build()
}