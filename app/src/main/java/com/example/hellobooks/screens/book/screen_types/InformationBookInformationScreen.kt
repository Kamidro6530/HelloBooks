package com.example.hellobooks.screens.book.screen_types

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.screens.book.BookInformationScreenTopMenu
import com.example.hellobooks.ui.theme.*


@Composable
fun BookDetailsScreen(book: Book) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        val mainInformationCardList = listOf(
            Pair(book.publicationDate, "Data publikacji"),
            Pair(book.categories, "Kategorie"),
            Pair(book.pages.toString(), "Liczba stron"),
            Pair(book.isbn, "Kod ISBN")
        )

        val additionalInformationCardList = listOf(
            Pair(book.publisher, "Wydawca"),
            Pair(book.language, "Język"),
            Pair(book.edition, "Edycja/Wydanie"),
            Pair(book.subtitle, "Podtytuł")
        )


        Spacer(modifier = Modifier.height(5.dp))
        if (book.description != "")
            DescriptionCard(book = book)

        if (mainInformationCardList.any { it.first != "" && it.first != "0" }) {
            Spacer(modifier = Modifier.height(5.dp))
            InformationCard(list = mainInformationCardList)
        }

        if (additionalInformationCardList.any { it.first != "" }) {
            Spacer(modifier = Modifier.height(5.dp))
            InformationCard(list = additionalInformationCardList)
        }


    }
}



@Composable
fun DescriptionCard(book: Book) {

    Card(
        modifier = Modifier
            .width(330.dp)
            .padding(vertical = 8.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = darkgreybackground,
            contentColor = primary
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InformationTextField(value = book.description, label = "O mnie")

            }
        }

    }
}

@Composable
fun InformationCard(list: List<Pair<String, String>>) {

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(330.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = darkgreybackground,
            contentColor = primary
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f)


        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(3.dp))
                InformationTextField(value = list[0].first, label = list[0].second)

                InformationTextField(value = list[1].first, label = list[1].second)

                InformationTextField(value = list[2].first, label = list[2].second)

                InformationTextField(value = list[3].first, label = list[3].second)

            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationTextField(value: String, label: String) {
    TextField(
        value = value,
        onValueChange = {
            value
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 10.sp,
                fontFamily = roboto_fonts,
                fontWeight = FontWeight.Light,
                color = primary
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = darkgreybackground,
            focusedIndicatorColor = darkgreybackground,
            unfocusedIndicatorColor = darkgreybackground, textColor = primary
        ),
        readOnly = true
    )
}