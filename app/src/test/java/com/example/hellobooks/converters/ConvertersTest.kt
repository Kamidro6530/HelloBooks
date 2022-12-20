package com.example.hellobooks.converters

import androidx.test.filters.SmallTest
import com.example.hellobooks.local.room.book.Book
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.params.provider.NullSource
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@SmallTest
@RunWith(RobolectricTestRunner::class)
internal class ConvertersTest {

    private fun String.encodeIllegalCharacters() : String {
        var string = this
        pairsIllegalAndCorrectCharacters.forEach {
            string =  string.replace(it.key, it.value)
        }
        return string
    }


    private fun String.decodeIllegalCharacters() : String {
        var string = this
        pairsIllegalAndCorrectCharacters.forEach{
            string =  string.replace(it.value,it.key)
        }
        return string
    }

    private fun replaceIllegalCharacters(book: Book): Book =
        book.apply {
            itemIdentifierOnlyForDownloadedBooks =
                itemIdentifierOnlyForDownloadedBooks?.encodeIllegalCharacters()
            description = description.encodeIllegalCharacters()
            imageUri = imageUri.encodeIllegalCharacters()
        }

    private val pairsIllegalAndCorrectCharacters : HashMap<Char,Char> = hashMapOf(
        '#' to 'µ',
        '%' to 'Ω',
        '&' to '∆',
        '{' to '—',
        '}' to '–',
        '<' to '‡',
        '>' to '†',
        '*' to '≥',
        '?' to '≤',
        '/' to '÷',
        ' ' to '‰',
        '$' to '¶',
        '!' to '√',
        "'".first() to '∏',
        '"' to '§',
        ':' to '∑',
        '@' to '≈',
        '+' to '≠',
        '`' to '±',
        '|' to 'º',
        '=' to '•',
    )

    @Test
    fun `encodeIllegalCharacters should not return any illegal character`(){
        //given
        var characters = pairsIllegalAndCorrectCharacters.keys.toString()
        //when
            pairsIllegalAndCorrectCharacters.forEach{
            characters  = characters.replace(it.key,it.value)
        }
        //then
        println("characters before change : ${pairsIllegalAndCorrectCharacters.keys}")
        println("characters after change : ${characters}")
        pairsIllegalAndCorrectCharacters.forEach{
            assertThat(characters).doesNotContain(it.key.toString())
        }

    }

    @Test
    fun `decodeIllegalCharacters should not return any legal character`(){
        //given
        var characters = pairsIllegalAndCorrectCharacters.values.toString()
        //when
        pairsIllegalAndCorrectCharacters.forEach{
            characters  = characters.replace(it.value,it.key)
        }
        //then
        println("characters before change : ${pairsIllegalAndCorrectCharacters.values}")
        println("characters after change : ${characters}")
        pairsIllegalAndCorrectCharacters.forEach{
            assertThat(characters).doesNotContain(it.value.toString())
        }

    }
    

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should not return empty String`() {
        //given
        val book = Book()
        val gson = Gson()
        replaceIllegalCharacters(book)
        //when-then
        assertThat(gson.toJson(book)).isNotEmpty()
        println(gson.toJson(book))
    }

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all slash marks from itemIdentifierOnlyForDownloadedBooks`() {
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
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all question marks from description`() {
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
    fun `parseBookIntoJsonToAllowSendAsArgument should replace all slash and question marks from imageUri`() {
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

    @Test
    fun `parseBookIntoJsonToAllowSendAsArgument should return Json String`(){
        //given
        val gson = Gson()
        val book = Book(title = "TestBook")
        replaceIllegalCharacters(book)
        //when
        val json = gson.toJson(book)
        //then
        assertThat(json).isInstanceOf(String::class.java)
        assertThat(gson.fromJson(json,Book::class.java)).isInstanceOf(Book::class.java)
    }

    @Test
    fun `parseJsonArgumentIntoBook should return Book object`() {
        //given
        val gson = Gson()
        val data = """
             {"author":"Joe","categories":"Music","description":"empty","edition":"","id":1,"imageUri":"uri","isbn":"","itShouldBeOnWishList":false,"itemIdentifierOnlyForDownloadedBooks":"","language":"en","manageBookInformation":{"userBookRating":0.0},"pages":248,"publicationDate":"2010-01-01","publisher":"Omnibus Press","subtitle":"","title":"Title"}
                    """
        //when
        val json = gson.fromJson(data.replace('`', '?').replace('^', '/'), Book::class.java)
        //then
        assertThat(json).isInstanceOf(Book::class.java)
    }

    @Test
    fun `parseJsonArgumentIntoBook should return empty Book object if json is empty`(){
        //given
        val gson = Gson()
        val data = """
             {"author":null,"categories":null,"description":null,"edition":null,"id":null,"imageUri":null,"isbn":null,"itShouldBeOnWishList":null,"itemIdentifierOnlyForDownloadedBooks":null,"language":null,"manageBookInformation":{"userBookRating":null},"pages":null,"publicationDate":null,"publisher":null,"subtitle":null,"title":null}
                    """
        //when
        val json = gson.fromJson(data.replace('`', '?').replace('^', '/'), Book::class.java) ?: Book()
        //then
        assertThat(json).isInstanceOf(Book::class.java)
        println(json)
    }

    @Test
    fun `parseJsonArgumentIntoBook should return empty Book if json is null`(){
        //given
        val gson = Gson()
        val json : String? = null
        //when
        val book = gson.fromJson(json, Book::class.java) ?: Book()
        //then
        assertThat(book).isNotNull()
    }

    @Test
    fun `encodeUriKey should not return string with '+' mark`(){
        //given
        var code = "+++132asdA+vc+++Asdasvz"
        //when
        code = code.encodeIllegalCharacters()
        //then
        assertThat(code).doesNotContain("+")
    }

    @Test
    fun `encodeUriKey should return 'null' String if code is null`(){
        //given
        var code : String? = null
        //when
        code = code?.encodeIllegalCharacters() ?: "null"
        //then
        assertThat(code).isNotNull()

    }

    @Test
    fun `decodeUriKey should not return string with '≠' mark`(){
        //given
        var code = "≠≠≠132asdA≠vc≠≠≠Asdasvz"
        //when
        code = code.decodeIllegalCharacters()
        //then
        assertThat(code).doesNotContain("≠")
    }






}