package com.example.hellobooks.repository

import androidx.test.filters.SmallTest
import com.example.hellobooks.local.room.book.Book
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FakeBookRepository : IBookRepository{

    private val booksList = mutableListOf<Book>()

     override fun getAllBooksFromDatabase() = flow{
        emit(booksList)
    }

    override suspend fun getBookFromBooksService(query_parameter: String): Flow<List<Book>?> {
        TODO("Not yet implemented")
    }


     @OptIn(ExperimentalCoroutinesApi::class)
     override fun insertBookToDatabase(book: Book) = runTest{
        booksList.add(book)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun deleteBookFromDatabase(book: Book) = runTest {
        booksList.remove(book)
    }

    private fun getBookFromBookService(query_parameter : String) = flow {
        emit(emptyList<Book>())
    }


}