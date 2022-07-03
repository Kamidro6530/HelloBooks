package com.example.hellobooks.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.screens.bookshelf.BookShelfListItem
import com.example.hellobooks.ui.theme.background
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookShelfScreen() {

    val bookViewModel = hiltViewModel<BookViewModel>()



    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        items(bookViewModel.listOfBooks) { book ->
            BookShelfListItem(book = book)


            Spacer(
                Modifier
                    .fillParentMaxWidth()
                    .height(1.dp)
                    .background(background)
                    .padding(vertical = 20.dp))

        }

    }

}
