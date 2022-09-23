package com.example.hellobooks.local.room.book

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title") var title: String= "",
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "publication_date") var publicationDate: String = "",
    @ColumnInfo(name = "categories") var categories: String = "",
    @ColumnInfo(name = "pages") var pages: Int = 0,
    @ColumnInfo(name = "isbn") var isbn: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    //Other options
    @ColumnInfo(name = "publisher") var publisher: String = "",
    @ColumnInfo(name = "language") var language: String = "",
    @ColumnInfo(name = "edition") var edition: String = "",
    @ColumnInfo(name = "subtitle") var subtitle: String = "",
    //Image
    @ColumnInfo(name = "image") var imageUri: String = "",



    )
