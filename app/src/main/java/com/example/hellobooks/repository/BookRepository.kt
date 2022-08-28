package com.example.hellobooks.repository

import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.local.room.BookDao
import com.example.hellobooks.remote.BookServiceImpl
import com.example.hellobooks.remote.BooksService
import com.example.hellobooks.remote.dto.BookResponse
import io.ktor.client.*
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(private val bookDao: BookDao,private val booksService: BooksService) {

     fun insertBook(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.insertBook(book)

        }
    }

     fun deleteBook(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.deleteBook(book)
        }
    }

    suspend fun getAllBooksFromDatabase() : Deferred<List<Book>> =
        CoroutineScope(Dispatchers.IO).async {
            bookDao.getAllBooks()
        }

    suspend fun getBook() : List<BookResponse>{
        return booksService.getBook()
    }





}