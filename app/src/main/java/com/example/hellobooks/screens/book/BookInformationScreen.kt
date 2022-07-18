package com.example.hellobooks.screens.book

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.hellobooks.R
import com.example.hellobooks.constants.Constants
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.room.book.Book
import java.util.*

@Composable
fun BookInformationScreen(jsonBook: String?) {
    val bookViewModel = hiltViewModel<BookViewModel>()
    Log.d("TEST", "JSON: ${jsonBook}")
    val book = bookViewModel.converters.jsonToBook(jsonBook)


    if (book?.imageUri.toString() == "null") {
        Image(
            painter = painterResource(id = R.drawable.picture_24),
            null,
            Modifier.size(30.dp)
        )
    } else {
        val image =Constants().galleryImagePath+ bookViewModel.converters.decodeUriKey(book!!.imageUri)
        Image(
            painter = rememberImagePainter(data = Uri.parse(image)),
            contentDescription = "",
            Modifier.size(height = 200.dp, width = 150.dp)
        )
    }





}