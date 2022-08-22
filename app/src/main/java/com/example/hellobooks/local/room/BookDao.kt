package com.example.hellobooks.local.room

import androidx.room.*
import com.example.hellobooks.local.room.book.Book

@Dao
interface BookDao {


    @Query("SELECT * FROM Book ")
    suspend fun getAllBooks() : List<Book>

    @Insert
    fun insertBook( book : Book)

    @Delete
    fun deleteBook(book: Book)
}