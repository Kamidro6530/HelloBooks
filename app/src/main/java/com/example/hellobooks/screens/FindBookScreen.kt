package com.example.hellobooks.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.screens.bookshelf.ItemContent
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun FindBookScreen() {
    val bookViewModel = hiltViewModel<BookViewModel>()

    Column() {
        SearchBar(bookViewModel = bookViewModel)
        ShowResults(bookViewModel = bookViewModel)
    }

}

@Composable
fun ShowResults(bookViewModel: BookViewModel) {
    val books = bookViewModel.searchBarResultsList.collectAsState()

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
           .fillMaxWidth()
           .wrapContentHeight()
    ) {
        items(books.value) {
            ItemContent(book = bookViewModel.converters.apiItemToBook(it!!))
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
