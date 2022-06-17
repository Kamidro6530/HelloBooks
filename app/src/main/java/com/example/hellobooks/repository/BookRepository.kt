package com.example.hellobooks.repository

import com.example.hellobooks.book.Book
import com.example.hellobooks.room.BookDao
import kotlinx.coroutines.*

class BookRepository(var bookDao: BookDao)  {

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