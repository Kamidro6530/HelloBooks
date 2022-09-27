package com.example.hellobooks.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.dto.VolumeInfo
import com.google.gson.Gson
import java.io.ByteArrayOutputStream


class Converters {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun bookToJson(book: Book): String? {
        val gson = Gson()
        //Method toJson have trouble with parse '?' char so I change this char to other
        book.description = book.description.replace('?', '`')
        book.imageUri = book.imageUri.replace('?', '`').replace('/', '^')
        return gson.toJson(book)
    }


    fun jsonToBook(json: String?): Book {
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

    fun apiItemToBook(volumeInfo: VolumeInfo): Book =
        Book().apply {
            title = volumeInfo.title ?: ""
            author = volumeInfo.authors?.joinToString("") ?: ""
            publicationDate = volumeInfo.publishedDate ?: ""
            categories = volumeInfo.categories?.joinToString("") ?: ""
            pages = volumeInfo.pageCount ?: 0
            isbn = ""
            description = volumeInfo.description ?: ""
            publisher = volumeInfo.publisher ?: ""
            language = volumeInfo.language ?: ""
            edition = ""
            subtitle = volumeInfo.subtitle ?: ""
            imageUri = volumeInfo.imageLinks?.thumbnail ?: ""
        }


}