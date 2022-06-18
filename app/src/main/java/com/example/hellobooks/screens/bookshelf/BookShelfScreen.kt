package com.example.hellobooks.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.*

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
                    Box(
                        Modifier
                            .background(color = darkgreybackground, shape = RoundedCornerShape(10.dp))
                            .size(height = 160.dp, width = 120.dp),
                            contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.picture_24),
                            null,
                            Modifier.size(30.dp)
                        )
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
                                text = "Title", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Black,
                                color = primary,
                                modifier = Modifier.padding(start = 70.dp, top = 5.dp, bottom = 10.dp),
                                fontSize = 22.sp
                            )
                        }
                        Row() {
                            Text(
                                text = "author", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(start = 15.dp, bottom = 5.dp, top = 5.dp)
                            )
                        }

                        Row() {
                            Text(
                                text = "publication date", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(start = 15.dp, bottom = 5.dp, top = 5.dp)
                            )
                        }
                        Row() {
                            Text(
                                text = "pages", fontFamily = roboto_fonts,
                                fontWeight = FontWeight.Normal,
                                color = primary,
                                modifier = Modifier.padding(start = 15.dp, bottom = 5.dp, top = 5.dp)
                            )
                        }
                    }

                }
            }
        }

    }
}
