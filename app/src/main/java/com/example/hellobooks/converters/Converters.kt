package com.example.hellobooks.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.room.TypeConverter
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.dto.VolumeInfo
import com.google.gson.Gson
import java.io.ByteArrayOutputStream


class Converters {


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
        ':' to '∑',
        '@' to '≈',
        '+' to '≠',
        '`' to '±',
        '|' to 'º',
        '=' to '•',
    )

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


    fun parseBookIntoJsonToAllowSendAsArgument(book: Book): String? {
        val gson = Gson()
        book.apply {
            itemIdentifierOnlyForDownloadedBooks = itemIdentifierOnlyForDownloadedBooks?.encodeIllegalCharacters()
            description = description.encodeIllegalCharacters()
            imageUri = imageUri.encodeIllegalCharacters()
        }
        return gson.toJson(book)
    }


    fun parseJsonArgumentIntoBook(json: String?): Book {
        val gson = Gson()
        return gson.fromJson(json?.decodeIllegalCharacters(), Book::class.java) ?: Book()
    }


    fun encodeUriKey(code: String?): String {
        return code?.encodeIllegalCharacters() ?: "null"
    }

    fun decodeUriKey(code: String): String {
        return code.decodeIllegalCharacters()
    }




}