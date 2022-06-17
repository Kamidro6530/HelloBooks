package com.example.hellobooks.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.roboto_fonts
import com.example.hellobooks.ui.theme.tertiary

@Composable
fun BookShelfScreen() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(background),
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
                    Image(
                        painter = painterResource(id = R.drawable.picture_24),
                        null,
                        Modifier
                            .background(tertiary)
                            .size(height = 200.dp, width = 150.dp)
                    )
                    //Informations next to image
                    Column() {
                        Row() {
                            Text(
                                text = "Something", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary
                            )
                        }
                        Row() {
                            Text(
                                text = "something", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary
                            )
                        }
                    }

                }
            }
        }

    }
}
