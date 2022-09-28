package com.example.hellobooks.screens.bookshelf

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.R
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmDeleteBook(book: Book, dismissState: DismissState) {

    val bookViewModel = hiltViewModel<BookViewModel>()
    val openDialog = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {


            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    Modifier
                        .background(background)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.books_24),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(
                            color = primary
                        ),
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .height(70.dp)
                            .fillMaxWidth(),

                        )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Jesteś pewny że chcesz usunąć książkę ?",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 2,
                            color = primary,
                            fontFamily = roboto_fonts,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                            // overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Odwrócenie zmian nie będzie już możliwe",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = primary,
                            fontFamily = roboto_fonts,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(dialogbuttonbackgroundcolors),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        TextButton(onClick = {

                            scope.launch {
                                dismissState.reset()
                            }
                            openDialog.value = false
                        }) {

                            Text(
                                "Nie chce usuwać",
                                fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Medium,
                                color = deleteItemColor,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                            )
                        }


                        TextButton(onClick = {
                            openDialog.value = false
                            bookViewModel.listOfBooks.remove(book)
                            scope.launch {
                                bookViewModel.deleteBook(book)
                                dismissState.reset()
                            }
                        }) {
                            Text(
                                "Tak chcę usunąć",
                                fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Bold,
                                color = background,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

