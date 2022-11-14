package com.example.hellobooks.screens.book

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.ScreenType
import com.example.hellobooks.screens.book.screen_types.BookDetailsScreen
import com.example.hellobooks.screens.book.screen_types.ManageBookInformationScreen
import com.example.hellobooks.screens.book.screen_types.StatsBookInformationScreen
import com.example.hellobooks.ui.theme.*
import java.lang.Exception


@Composable
fun BookInformationScreen(jsonBook: String?, navController: NavHostController, route: String?,scrrenType : String?) {

    BackHandler() {
       when(route){
           Routes.BookShelfScreen.route -> {navController.navigate(Routes.BookShelfScreen.route)}
           Routes.WishListScreen.route -> {navController.navigate(Routes.WishListScreen.route)}
           Routes.FindBookScreen.route -> {navController.navigate(Routes.FindBookScreen.route)}
       }
    }

    val bookViewModel = hiltViewModel<BookViewModel>()
    val book = bookViewModel.converters.parseJsonArgumentIntoBook(jsonBook)
    val imageFromGallery = Constants.GALLERY_IMAGE_PATH + bookViewModel.converters.decodeUriKey(book.imageUri)
    val scrollState = rememberScrollState()


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
            MainInformationCard(book = book)

            Spacer(modifier = Modifier.height(5.dp))

            BookInformationScreenTopMenu(
                navController = navController,
                jsonBook = jsonBook,
                route = route
            )


        when(scrrenType){
            ScreenType.Information.name -> {BookDetailsScreen(book = book)}
            ScreenType.Manage.name -> {ManageBookInformationScreen()}
            ScreenType.Statistics.name -> {StatsBookInformationScreen()}
            else -> { throw Exception("To navigate to BookInformationScreen use method withArgsAndScreenType")}
        }




        }
        ShowButtonsIfUserComingFromOtherScreenThanBookShelfScreen(
            jsonBook = jsonBook,
            navController = navController,
            route = route
        )
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




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowButtonsIfUserComingFromOtherScreenThanBookShelfScreen(jsonBook: String?, navController: NavHostController, route: String?) {
    val bookViewModel = hiltViewModel<BookViewModel>()
    val book = bookViewModel.converters.parseJsonArgumentIntoBook(jsonBook)
    val wishListOfBooks = bookViewModel.listOfBooksForWishList.value

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
            OutlinedIconButton(onClick = {
                when(wishListOfBooks.any{wishListBook -> wishListBook.itemIdentifierOnlyForDownloadedBooks == book.itemIdentifierOnlyForDownloadedBooks}){
                    true -> {
                        wishListOfBooks.find { it.itemIdentifierOnlyForDownloadedBooks == book.itemIdentifierOnlyForDownloadedBooks }
                            ?.let { bookViewModel.deleteBookFromDatabase(it) }
                    }
                    false -> {
                        bookViewModel.insertBookToDatabase(
                            Book(
                                0,
                                book.title,
                                book.author,
                                book.publicationDate,
                                book.categories,
                                book.pages,
                                book.isbn,
                                book.description,
                                book.publisher,
                                book.language,
                                book.edition,
                                book.subtitle,
                                book.imageUri,
                                true,
                                book.itemIdentifierOnlyForDownloadedBooks
                            )
                        )
                    }

                }

            }, border = BorderStroke(0.dp, color = Color.Transparent)) {
                Icon(
                    when(wishListOfBooks.any{wishListBook -> wishListBook.itemIdentifierOnlyForDownloadedBooks == book.itemIdentifierOnlyForDownloadedBooks}){
                        true -> {painterResource(id = R.drawable.fill_heart_24)}
                        false ->{ painterResource(id = R.drawable.heart_24) }},
                    contentDescription = "Add book to wishList"
                )

            }
        }
    }
}


