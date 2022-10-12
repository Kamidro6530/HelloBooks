package com.example.hellobooks.repository

import com.example.hellobooks.local.room.BookDao
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.BooksService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val booksService: BooksService
) {

    fun insertBook(book: Book) = CoroutineScope(Dispatchers.IO).launch {bookDao.insertBook(book)}


    fun deleteBook(book: Book) = CoroutineScope(Dispatchers.IO).launch {  bookDao.deleteBook(book) }

    fun getAllBooksFromDatabase(): Flow<List<Book>> = bookDao.getAllBooksFromDatabase()


    suspend fun getBook(query_parameter: String): Flow<List<Book>?> {
        return flow {
            val data = booksService.getBooks(query_parameter).items?.map { Book().apply {
                val book = it.volumeInfo
                title = book?.title ?: ""
                author = book?.authors?.joinToString("") ?: ""
                publicationDate = book?.publishedDate ?: ""
                categories = book?.categories?.joinToString("") ?: ""
                pages = book?.pageCount ?: 0
                isbn = ""
                description = book?.description ?: ""
                publisher = book?.publisher ?: ""
                language = book?.language ?: ""
                edition = ""
                subtitle = book?.subtitle ?: ""
                imageUri = book?.imageLinks?.thumbnail ?: ""
                wishList = true
                apiId = it.id
            } }

            emit(data)
        }.flowOn(Dispatchers.IO)
    }


}

