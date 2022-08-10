package com.example.hellobooks.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.hellobooks.R
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.room.book.Book
import com.example.hellobooks.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AddNewBookScreen(navController : NavHostController) {


    val bookViewModel = hiltViewModel<BookViewModel>()

    //Book parameters
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var author by remember { mutableStateOf(TextFieldValue("")) }
    var publicationDate by remember { mutableStateOf(TextFieldValue("")) }
    var pages by remember { mutableStateOf(TextFieldValue("0")) }
    var categories by remember { mutableStateOf(TextFieldValue("")) }
    var isbn by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    //Other options
    var publisher by remember { mutableStateOf(TextFieldValue("")) }
    var language by remember { mutableStateOf(TextFieldValue("")) }
    var edition by remember { mutableStateOf(TextFieldValue("")) }
    var subtitle by remember { mutableStateOf(TextFieldValue("")) }
    //Image
    var imageUri by remember { mutableStateOf<Uri?>(null) }


    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {


        //Title row
        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(
                text = "Dodaj nową książkę",
                fontFamily = roboto_fonts,
                fontWeight = FontWeight.Bold,
                color = primary
            )
        }


        //Book details row
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 30.dp, vertical = 15.dp)

        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(500.dp)
                    .background(background),
            ) {
                Column {
                    Row {
                        TextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Tytuł",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),

                            maxLines = 1
                        )

                    }

                    Row {
                        TextField(
                            value = author,
                            onValueChange = {
                                author = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Autor / Autorzy",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),

                            maxLines = 1
                        )

                    }
                    Row {
                        TextField(
                            value = publicationDate,
                            onValueChange = {
                                publicationDate = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Data wydania (rrrr-mm-dd)",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),

                            maxLines = 1
                        )

                    }
                    Row {
                        TextField(
                            value = pages,
                            onValueChange = {
                                pages = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Liczba stron",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            maxLines = 1
                        )

                    }
                    Row {
                        TextField(
                            value = categories,
                            onValueChange = {
                                categories = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Kategorie",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),

                            maxLines = 1
                        )

                    }
                    Row {

                        TextField(
                            value = isbn,
                            onValueChange = {
                                isbn = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "ISBN",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Light,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary
                            ),

                            maxLines = 1
                        )

                    }
                    Row {
                        TextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = {
                                Text(
                                    text = "Opis",
                                    fontFamily = roboto_fonts,
                                    fontWeight = FontWeight.Thin,
                                    color = primary
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = background, //hide the indicator
                                unfocusedIndicatorColor = background, textColor = primary
                            ),
                        )


                    }


                }


            }

        }


        //Show other options button

        var showOtherOptions by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 30.dp)
                .wrapContentHeight()
        ) {
            Button(
                onClick = { showOtherOptions = showOtherOptions.not() },
                colors = ButtonDefaults.buttonColors(containerColor = background),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                Icon(Icons.Filled.Add, "Other options", tint = primary)

            }
        }


        //Other details Row
        if (showOtherOptions == true) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 30.dp, vertical = 15.dp)


            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(500.dp)
                        .background(background),
                ) {
                    Column {
                        Row {
                            TextField(
                                value = publisher,
                                onValueChange = {
                                    publisher = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .padding(horizontal = 8.dp),
                                label = {
                                    Text(
                                        text = "Wydawca",
                                        fontFamily = roboto_fonts,
                                        fontWeight = FontWeight.Light,
                                        color = primary
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = background,
                                    focusedIndicatorColor = primary, //hide the indicator
                                    unfocusedIndicatorColor = primary, textColor = primary
                                ),

                                maxLines = 1
                            )

                        }

                        Row {
                            TextField(
                                value = language,
                                onValueChange = {
                                    language = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .padding(horizontal = 8.dp),
                                label = {
                                    Text(
                                        text = "Język",
                                        fontFamily = roboto_fonts,
                                        fontWeight = FontWeight.Light,
                                        color = primary
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = background,
                                    focusedIndicatorColor = primary, //hide the indicator
                                    unfocusedIndicatorColor = primary, textColor = primary
                                ),

                                maxLines = 1
                            )

                        }
                        Row {
                            TextField(
                                value = edition,
                                onValueChange = {
                                    edition = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .padding(horizontal = 8.dp),
                                label = {
                                    Text(
                                        text = "Edycja/Wydanie",
                                        fontFamily = roboto_fonts,
                                        fontWeight = FontWeight.Light,
                                        color = primary
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = background,
                                    focusedIndicatorColor = primary, //hide the indicator
                                    unfocusedIndicatorColor = primary, textColor = primary
                                ),

                                maxLines = 1
                            )

                        }
                        Row {
                            TextField(
                                value = subtitle,
                                onValueChange = {
                                    subtitle = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .padding(horizontal = 8.dp),
                                label = {
                                    Text(
                                        text = "Podtytuł",
                                        fontFamily = roboto_fonts,
                                        fontWeight = FontWeight.Light,
                                        color = primary
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = background,
                                    focusedIndicatorColor = primary, //hide the indicator
                                    unfocusedIndicatorColor = primary, textColor = primary
                                ),

                                maxLines = 1
                            )

                        }


                    }


                }

            }
        }
        //Image Picker row
        Row(
            Modifier
                .wrapContentHeight()
                .padding(horizontal = 30.dp, vertical = 25.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(500.dp)
                    .background(background),

                ) {
                Column(
                    Modifier
                        .align(Alignment.Center)
                        .padding(10.dp)
                ) {

                    Row() {


                        val context = LocalContext.current
                         val selectImageLauncher = rememberLauncherForActivityResult(
                            ActivityResultContracts.OpenDocument()
                        ) { uri ->
                             //Long-term access to Uri
                             if (uri != null) {
                                 context.contentResolver.takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
                             }

                             imageUri = uri

                        }

                        Button(
                            onClick = { selectImageLauncher.launch(arrayOf("image/*")) },
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = background,
                                contentColor = darkgreybackground
                            ),
                            modifier = Modifier
                                .size(height = 200.dp, width = 150.dp),

                            shape = RectangleShape,
                            border = BorderStroke(
                                2.dp,
                                darkgreybackground
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(background)
                            ) {
                                if (imageUri == null)
                                    Image(painterResource(id = R.drawable.picture_24), "Pick image")
                                else {

                                   Image(painter = rememberImagePainter(data = Uri.parse(imageUri.toString())), contentDescription = "",Modifier.size(height = 200.dp, width = 150.dp))

                                }

                            }
                        }

                    }


                }


            }
        }
        //Create new book button
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp, bottom = 15.dp)
        ) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        //Get only unique value from uri and insert to database(not able to send full uri for navigation)

                            val uniqueKey = imageUri?.toString()?.split("/image")
                        bookViewModel.insertBook(
                            Book(
                                0,
                                title.text,
                                author.text,
                                publicationDate.text,
                                categories.text,
                                pages.text.toInt(),
                                isbn.text,
                                description.text,
                                publisher.text,
                                language.text,
                                edition.text,
                                subtitle.text,
                                bookViewModel.converters.encodeUriKey(uniqueKey?.get(1))   // Image
                            )
                        )
                    }
                    navController.navigate(Routes.BookShelfScreen.route)

                },
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = tertiary),
            ) {
                Text(
                    text = "Dodaj książke ",
                    modifier = Modifier.padding(10.dp),
                    color = primary,
                    fontSize = 16.sp,
                    fontFamily = roboto_fonts,
                    fontWeight = FontWeight.Bold
                )

            }
        }

    }


}




