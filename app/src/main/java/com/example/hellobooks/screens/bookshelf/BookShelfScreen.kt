package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.screens.bookshelf.bookshelf_items.EmptyListBookShelfItem
import com.example.hellobooks.ui.theme.background

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookShelfScreen() {

    val bookViewModel = hiltViewModel<BookViewModel>()



    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        if (bookViewModel.listOfBooks.isEmpty()) {
            item {
                EmptyListBookShelfItem()
            }
        } else {
            items(bookViewModel.listOfBooks) { book ->
                BookShelfListItem(book = book)

                Spacer(
                    Modifier
                        .fillParentMaxWidth()
                        .height(1.dp)
                        .background(background)
                        .padding(vertical = 20.dp)
                )

            }

        }
    }

}
