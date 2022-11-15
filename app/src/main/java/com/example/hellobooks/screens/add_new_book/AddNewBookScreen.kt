package com.example.hellobooks.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.hellobooks.R
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewBookScreen(navController : NavHostController,bookAsJson : String? = "") {
    val scrollState = rememberScrollState()
    val bookViewModel = hiltViewModel<BookViewModel>()
    //If user coming from BottomBar text fields are empty
    val book = if (bookAsJson!="{book}") bookViewModel.converters.parseJsonArgumentIntoBook(bookAsJson) else createEmptyBookObjectBecauseNavigationArgumentDoNotHaveAnyArguments()
    //Book parameters
    var title by remember { mutableStateOf(TextFieldValue(book.title)) }
    var author by remember { mutableStateOf(TextFieldValue(book.author)) }
    var publicationDate by remember { mutableStateOf(TextFieldValue(book.publicationDate)) }
    var pages by remember { mutableStateOf(TextFieldValue(book.pages.toString())) }
    var categories by remember { mutableStateOf(TextFieldValue(book.categories)) }
    var isbn by remember { mutableStateOf(TextFieldValue(book.isbn)) }
    var description by remember { mutableStateOf(TextFieldValue(book.description)) }
    //Other options
    var publisher by remember { mutableStateOf(TextFieldValue(book.publisher)) }
    var language by remember { mutableStateOf(TextFieldValue(book.language)) }
    var edition by remember { mutableStateOf(TextFieldValue(book.edition)) }
    var subtitle by remember { mutableStateOf(TextFieldValue(book.subtitle)) }
    //Image
    var imageUri by remember { mutableStateOf<Uri?>(Uri.parse(book.imageUri)) }


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
                        CustomTextFieldForAddNewBookScreen(
                            descriptionText = "Tytuł",
                            onTextChange = {title = it},
                            textFieldValue = title
                        )

                    }

                    Row {
                        CustomTextFieldForAddNewBookScreen(
                            descriptionText = "Autor / Autorzy",
                            onTextChange = {author = it},
                            textFieldValue = author
                        )

                    }
                    Row {
                       CustomTextFieldForAddNewBookScreen(
                           descriptionText = "Data wydania (rrrr-mm-dd)",
                           onTextChange = {publicationDate = it} ,
                           textFieldValue = publicationDate
                       )

                    }
                    Row {
                        CustomTextFieldForAddNewBookScreen(
                            descriptionText = "Liczba stron",
                            onTextChange = {pages = it} ,
                            textFieldValue = pages
                        )

                    }
                    Row {
                       CustomTextFieldForAddNewBookScreen(
                           descriptionText = "Kategorie",
                           onTextChange = {categories = it},
                           textFieldValue = categories
                       )

                    }
                    Row {
                        CustomTextFieldForAddNewBookScreen(
                            descriptionText = "ISBN",
                            onTextChange = {isbn = it},
                            textFieldValue = isbn
                        )
                    }
                    Row {
                        CustomTextFieldForAddNewBookScreen(
                            descriptionText = "Opis",
                            onTextChange = {description = it},
                            textFieldValue = description,
                            fontWeight = FontWeight.Thin
                        )

                    }


                }


            }

        }


        //Show other options button
        var showMoreOptions by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 30.dp)
                .wrapContentHeight()
        ) {
            Button(
                onClick = { showMoreOptions = showMoreOptions.not() },
                colors = ButtonDefaults.buttonColors(containerColor = background),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                Icon(Icons.Filled.Add, "Other options", tint = primary)
            }
        }


        //Other details Row
        if (showMoreOptions == true) {
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
                            CustomTextFieldForAddNewBookScreen(
                                descriptionText = "Wydawca",
                                onTextChange = {publisher = it},
                                textFieldValue = publisher
                            )

                        }

                        Row {
                            CustomTextFieldForAddNewBookScreen(
                                descriptionText =  "Język",
                                onTextChange = {language = it},
                                textFieldValue = language
                            )

                        }
                        Row {
                            CustomTextFieldForAddNewBookScreen(
                                descriptionText = "Edycja/Wydanie",
                                onTextChange = {edition = it},
                                textFieldValue = edition
                            )

                        }
                        Row {
                            CustomTextFieldForAddNewBookScreen(
                                descriptionText =  "Podtytuł",
                                onTextChange = {subtitle = it},
                                textFieldValue = subtitle
                            )

                        }


                    }


                }

            }
        }



        Row(
            Modifier
                .wrapContentHeight()
                .padding(horizontal = 30.dp, vertical = 25.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            GalleryImagePicker(onImagePick = {imageUri = it}, imageUri = imageUri )
        }



        
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp, bottom = 15.dp)
        ) {
            val bookForInsertToDatabase =  Book(
                book.id,
                title.text,
                author.text,
                publicationDate.text,
                categories.text,
                if(pages.text.contains("[a-z]".toRegex()) || pages.text.contains("[A-Z]".toRegex())) 0 else pages.text.toInt(),
                isbn.text,
                description.text,
                publisher.text,
                language.text,
                edition.text,
                subtitle.text,
                defineWhatIsImageSourceAndPrepareForSendingByNavigationAndReturnCorrectUri(imageUri,bookViewModel)
            )

            CreateNewBookButton(bookViewModel = bookViewModel, navController = navController, book = bookForInsertToDatabase)

        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldForAddNewBookScreen(descriptionText : String, onTextChange: (TextFieldValue) -> Unit,textFieldValue: TextFieldValue,fontWeight: FontWeight = FontWeight.Light) {

    TextField(
        value = textFieldValue,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(horizontal = 8.dp),
        label = {
            Text(
                text = descriptionText,
                fontFamily = roboto_fonts,
                fontWeight = fontWeight,
                color = primary
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = background,
            focusedIndicatorColor = primary, //hide the indicator
            unfocusedIndicatorColor = primary, textColor = primary
        ),

        maxLines = if (descriptionText != "Opis") 1 else 10

    )

}

@Composable
fun GalleryImagePicker(onImagePick :(Uri?) -> Unit,imageUri: Uri?) {
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


                val context = LocalContext.current
                val selectImageLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.OpenDocument()
                ) { uri ->
                    //Long-term access to Uri
                    if (uri != null) {
                        context.contentResolver.takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }

                    onImagePick(uri)

                }

                Button(
                    onClick = { selectImageLauncher.launch(arrayOf("image/*")) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = background,
                        contentColor = darkgreybackground
                    ),
                    modifier = Modifier
                        .size(height = 200.dp, width = 150.dp),

                    shape = RectangleShape,
                    border = BorderStroke(2.dp,darkgreybackground)
                ) {
                    Box(
                        modifier = Modifier
                            .background(background)
                    ) {
                        if (imageUri == null)
                            Image(painterResource(id = R.drawable.picture_24), "Pick image")
                        else
                            Image(painter = rememberImagePainter(data = Uri.parse(imageUri.toString())), contentDescription = "",Modifier.size(height = 200.dp, width = 150.dp))


                    }
                }

            }


        }


    }
}

@Composable
fun CreateNewBookButton(bookViewModel: BookViewModel,navController: NavHostController,book: Book) {
    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                bookViewModel.insertBookToDatabase(
                   book
                )
            }
            navController.navigate(Routes.BookShelfScreen.route)

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




fun createEmptyBookObjectBecauseNavigationArgumentDoNotHaveAnyArguments() = Book()

fun defineWhatIsImageSourceAndPrepareForSendingByNavigationAndReturnCorrectUri(imageUri: Uri?,bookViewModel : BookViewModel) : String{
    Log.d("TEST", "TEST: $imageUri")
    val uniqueKeyPositionInImageUriPath = 1
    //Get only unique value from uri and insert to database(not able to send full uri for navigation)

    val uniqueImageUriKey = if(imageUri.toString() != "")
            (imageUri.toString().split("/image")).get(uniqueKeyPositionInImageUriPath)
        else
            ""


    fun imageIsFromApi() = imageUri.toString()
    fun imageIsFromGallery() = bookViewModel.converters.encodeUriKey(uniqueImageUriKey)

    return if (imageUri.toString().contains("http"))
        imageIsFromApi()
    else
        imageIsFromGallery()

}






