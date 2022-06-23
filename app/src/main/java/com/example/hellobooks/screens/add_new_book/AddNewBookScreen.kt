package com.example.hellobooks.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hellobooks.R
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun AddNewBookScreen() {


    val viewModel = hiltViewModel<BookViewModel>()

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
                        var title by remember { mutableStateOf(TextFieldValue("")) }
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
                        var author by remember { mutableStateOf(TextFieldValue("")) }
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
                        var publicationDate by remember { mutableStateOf(TextFieldValue("")) }
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
                        var pages by remember { mutableStateOf(TextFieldValue("")) }
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
                        var categories by remember { mutableStateOf(TextFieldValue("")) }
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
                        var isbn by remember { mutableStateOf(TextFieldValue("")) }
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
                        var description by remember { mutableStateOf(TextFieldValue("")) }
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
                            var publisher by remember { mutableStateOf(TextFieldValue("")) }
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
                            var language by remember { mutableStateOf(TextFieldValue("")) }
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
                            var edition by remember { mutableStateOf(TextFieldValue("")) }
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
                            var subtitle by remember { mutableStateOf(TextFieldValue("")) }
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

                        //Adress uri for image
                        var imageUri by remember { mutableStateOf<Uri?>(null) }

                        val context = LocalContext.current
                        val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                        val launcher =
                            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                                imageUri = uri
                            }

                        imageUri?.let {
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap.value = MediaStore.Images
                                    .Media.getBitmap(context.contentResolver, it)

                            } else {
                                val source = ImageDecoder
                                    .createSource(context.contentResolver, it)
                                bitmap.value = ImageDecoder.decodeBitmap(source)
                            }


                        }

                        Button(
                            onClick = { launcher.launch("image/") },
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
                                if (bitmap.value == null)
                                    Image(painterResource(id = R.drawable.picture_24), "Pick image")
                                else {

                                    bitmap.value?.let { btm ->
                                        Image(
                                            bitmap = btm.asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier.size(
                                                height = 200.dp,
                                                width = 150.dp
                                            )
                                        )
                                    }

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
                    // bookViewModel.insertBook(Book(1, title = title))
                    // navController.navigate(Routes.BookShelfScreen.route)
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
