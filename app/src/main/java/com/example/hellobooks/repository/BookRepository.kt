package com.example.hellobooks.repository

import android.util.Log
import com.example.hellobooks.local.room.BookDao
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.BooksService
import com.example.hellobooks.remote.dto.Item
import com.example.hellobooks.remote.dto.VolumeInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val booksService: BooksService
) {

    fun insertBook(book: Book) {
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.insertBook(book)

        }
    }

    fun deleteBook(book: Book) {
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.deleteBook(book)
        }
    }

    suspend fun getAllBooksFromDatabase(): Deferred<List<Book>> =
        CoroutineScope(Dispatchers.IO).async {
            bookDao.getAllBooks()
        }

    suspend fun getBook(query_parameter: String): Flow<List<VolumeInfo?>?> {
        return flow {
            val data = booksService.getBooks(query_parameter).items?.map { it.volumeInfo }
            emit(data)
        }.flowOn(Dispatchers.IO)
    }


}