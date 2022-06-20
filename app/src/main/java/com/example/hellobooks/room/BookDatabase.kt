package com.example.hellobooks.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hellobooks.room.book.Book


@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}