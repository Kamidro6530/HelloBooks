package com.example.hellobooks.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.ScreenType
import com.example.hellobooks.screens.bookshelf.ItemContent
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun FindBookScreen(navController: NavHostController) {
    val bookViewModel = hiltViewModel<BookViewModel>()

    Column() {
        SearchBar(bookViewModel = bookViewModel)
        ShowResults(bookViewModel = bookViewModel, navController)
    }

}

@Composable
fun ShowResults(bookViewModel: BookViewModel, navController: NavHostController) {
    val books = bookViewModel.searchBarResultsList.value

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(books) { book ->
            Row(Modifier.clickable {
                navController.navigate(
                    Routes.BookInformationScreen.withArgsAndScreenType(
                        bookViewModel.converters.bookToJson(book),
                        Routes.FindBookScreen.route,
                        screenType = ScreenType.Information
                    )
                )

            }) {

                ItemContent(book = book)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    placeholderText: String = "", bookViewModel: BookViewModel
) {
    var showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    var searchText by remember { mutableStateOf("") }

    TopAppBar(title = { Text("") }, navigationIcon = {
        Icon(
            imageVector = Icons.Filled.Search,
            modifier = Modifier.padding(start = 3.dp),
            contentDescription = "Search Icon"
        )

    }, actions = {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                },
            value = searchText,
            onValueChange = {
                CoroutineScope(Dispatchers.IO).launch { bookViewModel.getBook(it) }
                searchText = it
            },
            placeholder = {
                Text(text = placeholderText)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = background,
                unfocusedIndicatorColor = background,
                containerColor = background,
                cursorColor = primary
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = showClearButton,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear text"
                        )
                    }

                }
            },
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
        )
    }, backgroundColor = background)

}
