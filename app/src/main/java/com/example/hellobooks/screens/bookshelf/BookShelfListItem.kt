package com.example.hellobooks.screens.bookshelf

import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.hellobooks.R
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.room.book.Book
import com.example.hellobooks.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun BookShelfListItem(book: Book) {

    val bookViewModel = hiltViewModel<BookViewModel>()
    val listOfBooks = bookViewModel.listOfBooks

    val dismissState = rememberDismissState(initialValue = DismissValue.Default, confirmStateChange = {
        if (it == DismissValue.DismissedToStart){
            listOfBooks.remove(book)
            CoroutineScope(Dispatchers.IO).launch {
                bookViewModel.deleteBook(book)
            }
        }
        true
    })


    SwipeToDismiss(
        state = dismissState,
        background = {
            val color = when (dismissState.dismissDirection) {
                DismissDirection.StartToEnd -> darkgreybackground
                DismissDirection.EndToStart -> deleteItemColor
                null -> darkgreybackground
            }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = primary,
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(30.dp).size(30.dp)
                        )
                        Spacer(modifier = Modifier.heightIn(5.dp))


                    }
                }

        },
        /**** Dismiss Content */
        dismissContent = {
            ItemContent(book)
        },
        /*** Set Direction to dismiss */
        directions = setOf(DismissDirection.EndToStart),
    )
}


@Composable
fun ItemContent(book: Book) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 1.dp)
            .background(darkgreybackground)
    ) {
        Box() {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(), horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Box(
                        Modifier
                            .background(
                                color = darkgreybackground,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .size(height = 180.dp, width = 130.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (book.imageUri == "null") {
                            Image(
                                painter = painterResource(id = R.drawable.picture_24),
                                null,
                                Modifier.size(30.dp)
                            )
                        } else {

                            Image(
                                painter = rememberImagePainter(data = Uri.parse(book.imageUri)),
                                contentDescription = "",
                                Modifier.size(height = 200.dp, width = 150.dp)
                            )
                        }

                    }

                    //Informations next to image
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Row() {
                            Text(
                                text = book.title, fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Black,
                                color = primary,
                                modifier = Modifier.padding(start = 90.dp),
                                fontSize = 22.sp
                            )
                        }
                        Row(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 15.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.user_24),
                                contentDescription = null,
                                Modifier.size(20.dp)
                            )
                            Text(
                                text = book.author, fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(bottom = 4.dp, start = 10.dp)
                            )
                        }

                        Row(Modifier.padding(top = 10.dp, bottom = 10.dp, start = 15.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.calendar_24),
                                contentDescription = null,
                                Modifier.size(20.dp)
                            )
                            Text(
                                text = book.publicationDate, fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(bottom = 4.dp, start = 10.dp)
                            )
                        }
                        Row(Modifier.padding(top = 10.dp, start = 15.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.document_24),
                                contentDescription = null,
                                Modifier.size(20.dp)
                            )
                            Text(
                                text = book.pages.toString(), fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(bottom = 4.dp, start = 10.dp)
                            )
                        }
                    }

                }
            }
        }

    }
}