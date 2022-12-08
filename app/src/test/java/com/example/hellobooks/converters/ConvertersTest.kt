package com.example.hellobooks.converters

import androidx.test.filters.SmallTest
import com.example.hellobooks.local.room.book.Book
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@SmallTest
@RunWith(RobolectricTestRunner::class)
internal class ConvertersTest{

    private fun replaceIllegalCharacters(book: Book) : Book =
        book.apply {
            itemIdentifierOnlyForDownloadedBooks = itemIdentifierOnlyForDownloadedBooks?.replace('/', '^')
            description = description.replace('?', '`')
            imageUri = imageUri.replace('?', '`').replace('/', '^')
        }



    @Test
     fun `parseBookIntoJsonToAllowSendAsArgument should not return empty String`(){
        //given
        val book = Book()
        val gson = Gson()
        replaceIllegalCharacters(book)
        //when-then
        assertThat(gson.toJson(book)).isNotEmpty()
        println(gson.toJson(book))
    }

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all slash marks from itemIdentifierOnlyForDownloadedBooks`(){
        //given
        val book = Book(itemIdentifierOnlyForDownloadedBooks = "/////////////as/da/s//12/f/as/asd?")
        println("Unwanted mark : /")
        //when
        replaceIllegalCharacters(book)
        //then
        assertThat(book.itemIdentifierOnlyForDownloadedBooks).doesNotContain("/")
        println(book.itemIdentifierOnlyForDownloadedBooks)

    }

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all question marks from description`(){
        //given
        val book = Book(description = "???")
        println("Unwanted mark : ?")
        //when
        replaceIllegalCharacters(book)
        //then
        assertThat(book.description).doesNotContain("?")
        println(book.description)

    }

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all slash and question marks from imageUri`(){
        //given
        val book = Book(imageUri = "???///```321")
        println("Unwanted mark : ? and /")
        //when
        replaceIllegalCharacters(book)
        //then
        assertThat(book.imageUri).doesNotContain("/")
        assertThat(book.imageUri).doesNotContain("?")
        println(book.imageUri)

    }


}