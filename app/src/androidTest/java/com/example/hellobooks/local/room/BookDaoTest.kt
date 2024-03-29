package com.example.hellobooks.local.room

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.hellobooks.local.room.book.Book
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
internal class BookDaoTest {

    @Inject
    @Named("bookdb-test")
    lateinit var  database : BookDatabase

    @Inject
    @Named("bookdao-test")
   lateinit var dao : BookDao

   @get:Rule
   var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
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