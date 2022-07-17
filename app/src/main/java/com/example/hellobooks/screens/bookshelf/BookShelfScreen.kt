package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.screens.book.BookInformationScreen
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.screens.bookshelf.bookshelf_items.EmptyListBookShelfItem
import com.example.hellobooks.ui.theme.background
import javax.inject.Inject

@Composable
fun BookShelfScreen (navController: NavHostController) {

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
                Row(
                    Modifier
                        .wrapContentHeight()
                        .clickable(onClick = {
                            navController.navigate(Routes.BookInformationScreen.withArgs(bookViewModel.converters.bookToJson(book)))
                        })) {
                    BookShelfListItem(book = book)

               }
                Row() {

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

}
