package com.example.hellobooks.screens.book

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.hellobooks.R
import com.example.hellobooks.constants.Constants
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInformationScreen(jsonBook: String?, navController: NavHostController, route: String?) {
    val bookViewModel = hiltViewModel<BookViewModel>()
    val book = bookViewModel.converters.jsonToBook(jsonBook)
    

    val imageFromGallery =
        Constants.GALLERY_IMAGE_PATH + bookViewModel.converters.decodeUriKey(book.imageUri)

    val scrollState = rememberScrollState()
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

    Box(contentAlignment = Alignment.TopEnd) {

        if (book.imageUri == "null") {
            Modifier.background(background)
        } else {
            Image(
                painter =
                if (book.imageUri.contains("http"))
                    rememberImagePainter(data = book.imageUri.replace("1", "0"))
                else
                    rememberImagePainter(data = Uri.parse(imageFromGallery)),
                contentDescription = "background",
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }




        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            Spacer(modifier = Modifier.height(200.dp))


            MainInformationCard(book)
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

        //Column with options buttons
        if (route != Routes.BookShelfScreen.route) {
            Column {


                OutlinedIconButton(onClick = {
                    navController.navigate(
                        Routes.AddNewBookScreen.withArgs(
                            jsonBook
                        )
                    )
                }, border = BorderStroke(0.dp, color = Color.Transparent)) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_24),
                        contentDescription = "Add book button"
                    )

                }
                val wishListOfBooks = bookViewModel.listOfBooks.value.filter { it.wishList == true }

                OutlinedIconButton(onClick = {
                    if (wishListOfBooks.any { wishListBook -> wishListBook.apiId == book.apiId })
                        bookViewModel.deleteBook(book)
                    else
                        bookViewModel.insertBook(Book(0,book.title,book.author,book.publicationDate,book.categories,book.pages,book.isbn,book.description,book.publisher,book.language,book.edition,book.subtitle,book.imageUri,true,book.apiId))
                }, border = BorderStroke(0.dp, color = Color.Transparent)) {
                    Icon(
                        if (wishListOfBooks.any { wishListBook -> wishListBook.apiId == book.apiId }){
                            painterResource(id = R.drawable.fill_heart_24)
                        } else {
                            painterResource(id = R.drawable.heart_24)
                        },
                        contentDescription = "Add book to wishList"
                    )

                }
            }
        }
    }
}

@Composable
fun MainInformationCard(book: Book) {
    Card(
        modifier = Modifier
            .width(330.dp)
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = darkgreybackground,
            contentColor = primary
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Title
                Text(
                    book.title,
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    color = tertiary,
                    fontSize = 28.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    Modifier
                        .width(330.dp)
                        .height(1.dp)
                        .background(background)

                )
                //Author
                Text(
                    book.author,
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )


            }
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





