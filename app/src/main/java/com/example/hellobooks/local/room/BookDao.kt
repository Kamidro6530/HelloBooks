package com.example.hellobooks.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hellobooks.local.room.book.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun getAllBooksFromDatabase(): Flow<List<Book>>

    @Insert
    fun insertBookToDatabase(book: Book)

    @Delete
    fun deleteBookFromDatabase(book: Book)
}