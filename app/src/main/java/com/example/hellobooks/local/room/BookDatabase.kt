package com.example.hellobooks.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.local.room.book.Book


@Database(entities = [Book::class], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}