package com.example.hellobooks.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.Gravity.apply
import androidx.core.view.GravityCompat.apply
import androidx.room.TypeConverter
import com.example.hellobooks.room.book.Book
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
    fun toBitmap(byteArray: ByteArray) : Bitmap
    {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }

    fun bookToJson(book: Book) : String?{
        val gson = Gson()
        val bookObject = book.apply{
           Book(id, title, author, publicationDate, categories, pages, isbn, description, publisher, language,edition, subtitle, imageUri)
        }


        return gson.toJson(bookObject)
    }


    fun jsonToBook(json : String?) : Book?{
        val gson = Gson()
        return gson.fromJson(json,Book::class.java)
    }
}