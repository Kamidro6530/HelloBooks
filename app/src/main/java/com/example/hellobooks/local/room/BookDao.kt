package com.example.hellobooks.local.room

import androidx.room.*
import com.example.hellobooks.local.room.book.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun getAllBooksFromDatabase(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookToDatabase(book: Book)

    @Delete
    fun deleteBookFromDatabase(book: Book)
}