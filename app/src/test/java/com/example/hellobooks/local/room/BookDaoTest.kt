package com.example.hellobooks.local.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.hellobooks.local.room.book.Book
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@SmallTest
@RunWith(RobolectricTestRunner::class)
internal class BookDaoTest {

   private lateinit var  database : BookDatabase
   lateinit var dao : BookDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.bookDao()
    }

    @After
    fun teardown(){
        database.close()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun insertBookToDatabase() = runTest() {
        //given
        val book = Book(id= 1,title = "Test", pages = 200)
        //when
        dao.insertBookToDatabase(book)
        //then
        dao.getAllBooksFromDatabase().test{
           val books = awaitItem()
            assertThat(books).contains(Book(id=1,title = "Test", pages = 200))
            println(books.first())
        }


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
     fun getAllBooksFromDatabase() = runTest() {
        //given
        dao.insertBookToDatabase(Book(id= 1,title = "Test1", pages = 100))
        dao.insertBookToDatabase(Book(id= 2,title = "Test2", pages = 200))
        dao.insertBookToDatabase(Book(id= 3,title = "Test3", pages = 300))
        //when-then
        dao.getAllBooksFromDatabase().test {
            val books = awaitItem()
            assertThat(books).hasSize(3)
            println("List size : "+books.size)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteBookFromDatabase() = runTest() {
        //given
        val book = Book(id= 1,title = "Test1", pages = 100)
        dao.insertBookToDatabase(book)
        //when
        dao.deleteBookFromDatabase(book)
        //then
        dao.getAllBooksFromDatabase().test {
            val books = awaitItem()
            assertThat(books).isEmpty()
            println("List size : "+books.size)
        }
    }




}