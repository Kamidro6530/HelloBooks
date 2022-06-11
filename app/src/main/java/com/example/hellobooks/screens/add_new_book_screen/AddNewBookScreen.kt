package com.example.hellobooks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.roboto_fonts

@Composable
fun AddNewBookScreen() {

    Column() {

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Dodaj nową książkę")
        }
        //Book details column
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 15.dp)
                .fillMaxSize()
                .background(background)
                //.wrapContentHeight()
        ) {


            Row() {
                var title by remember { mutableStateOf(TextFieldValue("")) }
                Text(
                    text = "Tytuł",
                    color = primary,
                    fontFamily = roboto_fonts,
                    fontWeight = FontWeight.Black
                )
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    Modifier.height(23.dp),
                    label = null,
                    colors = TextFieldDefaults.textFieldColors(containerColor = background),
                    maxLines = 1
                )
            }

        }
    }
}