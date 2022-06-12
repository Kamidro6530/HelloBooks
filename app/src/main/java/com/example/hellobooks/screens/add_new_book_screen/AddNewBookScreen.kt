package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.roboto_fonts

@Composable
fun AddNewBookScreen() {

    Column(modifier = Modifier.fillMaxSize()) {

        //Title row
        Row(Modifier.align(Alignment.CenterHorizontally)) {
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
                .fillMaxSize()
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
                        var publicationYear by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            value = publicationYear,
                            onValueChange = {
                                publicationYear = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .padding(horizontal = 8.dp),
                            label = { Text(text = "Rok wydania",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
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
                                .padding(horizontal = 8.dp),
                            label = { Text(text = "Description",fontFamily = roboto_fonts,fontWeight = FontWeight.Light,color = primary) },
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