package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.darkgreybackground
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.roboto_fonts

@Composable
fun AddNewBookScreen() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {

        //Title row
        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)) {
            Text(
                text = "Dodaj nową książkę",
                fontFamily = roboto_fonts,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
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
                    .background(background)
                   ,
            ) {
                Column() {
                    Row() {
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
                            label = { Text(text = "Tytuł",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }

                    Row() {
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
                            label = { Text(text = "Autor / Autorzy",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
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
                            label = { Text(text = "Data wydania",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
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
                            label = { Text(text = "Kategorie",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
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
                            label = { Text(text = "ISBN",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
                        var description by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp)
                                ,
                            label = { Text(text = "Opis",fontFamily = roboto_fonts,fontWeight = FontWeight.Thin,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = background, //hide the indicator
                                unfocusedIndicatorColor = background, textColor = primary),
                        )


                    }


                }


            }

        }
        //Show other options button
        Row(modifier = Modifier.align(Alignment.End).padding(horizontal = 30.dp).wrapContentHeight()) {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = background), shape = RoundedCornerShape(6.dp), contentPadding = PaddingValues(4.dp)) {
                Icon(Icons.Filled.Add,"Other options", tint = primary)

            }
        }

        //Other details Row
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 30.dp, vertical = 15.dp)

        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(500.dp)
                    .background(background)
                ,
            ) {
                Column() {
                    Row() {
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
                            label = { Text(text = "Wydawca",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }

                    Row() {
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
                            label = { Text(text = "Język",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
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
                            label = { Text(text = "Edycja/Wydanie",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }
                    Row() {
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
                            label = { Text(text = "Podtytuł",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = background,
                                focusedIndicatorColor = primary, //hide the indicator
                                unfocusedIndicatorColor = primary, textColor = primary),

                            maxLines = 1
                        )

                    }



                }


            }

        }
    }
}