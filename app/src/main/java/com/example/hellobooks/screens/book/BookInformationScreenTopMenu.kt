package com.example.hellobooks.screens.book

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Routes
import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.ScreenType
import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.TopBarBookInformationScreenItems
import com.example.hellobooks.ui.theme.darkgreybackground
import com.example.hellobooks.ui.theme.primary

@Composable
fun BookInformationScreenTopMenu(jsonBook: String?, navController: NavHostController, route: String?){
    val bookViewModel = hiltViewModel<BookViewModel>()
    val book = bookViewModel.converters.jsonToBook(jsonBook)
    val items = listOf(
        TopBarBookInformationScreenItems.InfoBookInformationScreen,
        TopBarBookInformationScreenItems.ManageBookInformationScreen,
        TopBarBookInformationScreenItems.StatsBookInformationScreen
    )
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            androidx.compose.material3.Card(
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
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    items.forEach {
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = ""
                                )
                            },
                            onClick = { navController.navigate(
                               it.route.withArgsAndScreenType(
                                bookViewModel.converters.bookToJson(book),
                                route,
                                screenType = it.screenType
                            )) },
                            selected = false
                        )
                    }
                }
            }
        }
    )
}