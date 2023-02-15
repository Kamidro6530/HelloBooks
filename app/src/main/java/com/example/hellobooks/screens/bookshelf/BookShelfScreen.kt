package com.example.hellobooks.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.ScreenType
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.screens.bookshelf.BookShelfViewModel
import com.example.hellobooks.screens.bookshelf.BookShelfViewModelUiState
import com.example.hellobooks.screens.bookshelf.bookshelf_items.ErrorOrEmptyListBookShelfItem
import com.example.hellobooks.ui.theme.background

@Composable
fun BookShelfScreen(navController: NavHostController) {

    val bookShelfViewModel = hiltViewModel<BookShelfViewModel>()
    val bookShelfUiState = bookShelfViewModel.bookShelfUiState.collectAsState().value

    when(bookShelfUiState){
        is BookShelfViewModelUiState.Success -> ShowBooksList(bookShelfViewModel,navController,bookShelfUiState.data)
        is BookShelfViewModelUiState.Error -> ShowErrorScreen()
        is BookShelfViewModelUiState.Loading -> ShowErrorScreen()
    }

}

@Composable
fun ShowBooksList(bookShelfViewModel : BookShelfViewModel,navController : NavHostController,listOfBooks : List<Book>) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.fillMaxSize()
    ) {
        if (listOfBooks.isEmpty()) {
            item {
                ErrorOrEmptyListBookShelfItem(BookShelfViewModelUiState.Success(listOfBooks))
            }
        } else {
            items(listOfBooks) { book ->
                Row(
                    Modifier
                        .wrapContentHeight()
                        .clickable(onClick = {
                            navController.navigate(
                                Routes.BookInformationScreen.withArgsAndScreenTypeForBookInformationScreen(
                                    bookShelfViewModel.converters.parseBookIntoJsonToAllowSendAsArgument(
                                        book
                                    ),
                                    Routes.BookShelfScreen.route,
                                    screenType = ScreenType.Information
                                ),
                            )
                        })
                ) {
                    BookShelfListItem(jsonBook = bookShelfViewModel.converters.parseBookIntoJsonToAllowSendAsArgument(book))
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

@Composable
fun ShowErrorScreen() {
    ErrorOrEmptyListBookShelfItem(BookShelfViewModelUiState.Error("Wystąpił błąd sprawdź swoje połączenie z internetem"))
}
