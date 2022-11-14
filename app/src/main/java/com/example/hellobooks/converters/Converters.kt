package com.example.hellobooks.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.dto.VolumeInfo
import com.google.gson.Gson
import java.io.ByteArrayOutputStream


class Converters {

    fun parseBookIntoJsonToAllowSendAsArgument(book: Book): String? {
        val gson = Gson()
        //Method toJson have trouble with parse '?' char so I change this char to other
        book.apply {
            itemIdentifierOnlyForDownloadedBooks = itemIdentifierOnlyForDownloadedBooks?.replace('/', '^')
            description = description.replace('?', '`')
            imageUri = imageUri.replace('?', '`').replace('/', '^')
        }
        return gson.toJson(book)
    }


    fun parseJsonArgumentIntoBook(json: String?): Book {
        val gson = Gson()
        //Method toJson have trouble with parse '?' char so I change this char to other
        return gson.fromJson(json?.replace('`', '?')?.replace('^', '/'), Book::class.java)
    }

    fun decodeUriKey(code: String): String {
        return code.replace("+", "%")
    }

    fun encodeUriKey(code: String?): String {
        return code?.replace("%", "+") ?: "null"
    }




}