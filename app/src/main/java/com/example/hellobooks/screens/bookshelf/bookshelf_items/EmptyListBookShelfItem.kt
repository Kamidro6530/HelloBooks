package com.example.hellobooks.screens.bookshelf.bookshelf_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.darkgreybackground
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.roboto_fonts

@Composable
fun EmptyListBookShelfItem() {
    Box(
        Modifier
            .padding(vertical = 180.dp, horizontal = 10.dp)
            .fillMaxSize()
            .background(darkgreybackground), contentAlignment = Alignment.Center
    ) {
        Column() {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sad_24),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = primary
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp, bottom = 30.dp)
                        .height(70.dp)
                        .fillMaxWidth(),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    "O nie, na twojej półce nie ma żadnych książek ...",
                    Modifier.padding(15.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = roboto_fonts,
                    color = primary,
                    maxLines = 1
                )
            }
        }
    }
}