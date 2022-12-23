package com.example.hellobooks.repository

import com.example.hellobooks.local.room.book.Book
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface IBookRepository {

   fun insertBookToDatabase(book: Book)

    fun deleteBookFromDatabase(book: Book)

    fun getAllBooksFromDatabase(): Flow<List<Book>>

   suspend fun getBookFromBooksService(query_parameter: String): Flow<List<Book>?>
}