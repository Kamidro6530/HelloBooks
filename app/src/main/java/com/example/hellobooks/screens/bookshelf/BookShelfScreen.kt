package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.ui.theme.background

@Composable
fun BookShelfScreen() {

    val bookViewModel = hiltViewModel<BookViewModel>()
    val bookList = bookViewModel.listOfBooks.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()) {
        items(bookList.value){ book ->
            BookShelfListItem(book)

            Spacer(Modifier.fillParentMaxWidth().height(1.dp).background(background).padding(vertical = 20.dp))


        }

    }

}
