package com.example.hellobooks.repository

import androidx.test.filters.SmallTest
import com.example.hellobooks.local.room.book.Book
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test


internal class FakeBookRepository {

    private val booksList = mutableListOf<Book>()

    private fun getAllBooksFromDatabase() = flow{
        emit(booksList)
    }


    private fun insertBookToDatabase(book: Book) = runTest{
        booksList.add(book)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun deleteBookFromDatabase(book: Book) = runTest {
        booksList.remove(book)
    }

    private fun getBookFromBookService(query_parameter : String) = flow {
        emit(emptyList<Book>())
    }


}