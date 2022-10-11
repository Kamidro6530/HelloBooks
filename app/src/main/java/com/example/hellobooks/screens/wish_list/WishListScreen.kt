package com.example.hellobooks.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.screens.bookshelf.bookshelf_items.EmptyListBookShelfItem
import com.example.hellobooks.screens.wish_list.EmptyWishListItem
import com.example.hellobooks.ui.theme.background

@Composable
fun WishListScreen(navController: NavHostController) {
    val bookViewModel = hiltViewModel<BookViewModel>()
    val wishListOfBooks = bookViewModel.listOfBooks.value.filter { it.wishList == true  }

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.fillMaxSize()
    ) {
        if (wishListOfBooks.isEmpty()) {
            item {
                EmptyWishListItem()
            }
        } else {
            items(wishListOfBooks) { book ->
                Row(
                    Modifier
                        .wrapContentHeight()
                        .clickable(onClick = {
                            navController.navigate(
                                Routes.BookInformationScreen.withArgs(
                                    bookViewModel.converters.bookToJson(book),
                                    Routes.WishListScreen.route
                                ),
                            )
                        })
                ) {
                    BookShelfListItem(jsonBook = bookViewModel.converters.bookToJson(book))
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