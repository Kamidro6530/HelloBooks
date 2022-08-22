package com.example.hellobooks.repository

import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.local.room.BookDao
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(private val bookDao: BookDao) {

    suspend fun insertBook(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.insertBook(book)

        }
    }

    suspend fun deleteBook(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.deleteBook(book)
        }
    }

    suspend fun getAllBooks() : Deferred<List<Book>> =
        CoroutineScope(Dispatchers.IO).async {
            bookDao.getAllBooks()
        }



}