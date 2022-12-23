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
open class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val booksService: BooksService
) : IBookRepository {

    override fun insertBookToDatabase(book: Book) {
        CoroutineScope(Dispatchers.IO).launch { bookDao.insertBookToDatabase(book) }
    }


    override fun deleteBookFromDatabase(book: Book) {
        CoroutineScope(Dispatchers.IO).launch { bookDao.deleteBookFromDatabase(book) }
    }

    override fun getAllBooksFromDatabase(): Flow<List<Book>> = bookDao.getAllBooksFromDatabase()


    override suspend fun getBookFromBooksService(query_parameter: String): Flow<List<Book>?> {
        return flow {
            val dataToEmit = booksService.getBooksFromBooksService(query_parameter).items?.map {
                val book = it.volumeInfo
                Book().apply {
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
                    itShouldBeOnWishList = true
                    itemIdentifierOnlyForDownloadedBooks = it.id
                }
            }

            emit(dataToEmit)
        }.flowOn(Dispatchers.IO)
    }
}





