package com.example.hellobooks.repository

import com.example.hellobooks.room.book.Book
import com.example.hellobooks.room.BookDao
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(private val bookDao: BookDao) {

     fun insertBook(book : Book){
        bookDao.insertBook(book)
    }

     fun deleteBook(book : Book){
        bookDao.deleteBook(book)
    }

     fun getAllBooks() : Deferred<List<Book>> =
        CoroutineScope(Dispatchers.IO).async {
            bookDao.getAllBooks()
        }



}