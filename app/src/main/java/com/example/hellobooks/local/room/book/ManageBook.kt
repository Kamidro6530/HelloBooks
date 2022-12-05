package com.example.hellobooks.local.room.book

import androidx.room.Entity

@Entity
data class ManageBook(
     val userBookRating : Float = 0F
)
