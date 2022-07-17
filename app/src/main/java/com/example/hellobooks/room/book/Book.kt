package com.example.hellobooks.room.book

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hellobooks.R

@Entity()
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title") val title: String= "",
    @ColumnInfo(name = "author") val author: String = "",
    @ColumnInfo(name = "publication_date") val publicationDate: String = "",
    @ColumnInfo(name = "categories") val categories: String = "",
    @ColumnInfo(name = "pages") val pages: Int = 0,
    @ColumnInfo(name = "isbn") val isbn: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    //Other options
    @ColumnInfo(name = "publisher") val publisher: String = "",
    @ColumnInfo(name = "language") val language: String = "",
    @ColumnInfo(name = "edition") val edition: String = "",
    @ColumnInfo(name = "subtitle") val subtitle: String = "",
    //Image
    @ColumnInfo(name = "image") val imageUri: String ,



    )
