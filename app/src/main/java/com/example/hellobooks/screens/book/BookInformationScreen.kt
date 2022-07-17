package com.example.hellobooks.screens.book

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.room.book.Book
import java.util.*

@Composable
fun BookInformationScreen(jsonBook: String?) {
    val bookViewModel = hiltViewModel<BookViewModel>()
    val book = bookViewModel.converters.jsonToBook(jsonBook)






}