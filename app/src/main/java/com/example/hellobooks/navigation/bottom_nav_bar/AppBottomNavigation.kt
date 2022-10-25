package com.example.hellobooks.navigation.bottom_nav_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.hellobooks.navigation.navigation_routes_items.BottomBarItems

@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    bottomBarVisibilityState : MutableState<Boolean>
) {
    val bottomBarItems = listOf(
        BottomBarItems.BookShelfScreen,
        BottomBarItems.FindBookScreen,
        BottomBarItems.AddNewBookScreen,
        BottomBarItems.WishListScreen,
        BottomBarItems.StatisticsScreen
    )

    AnimatedVisibility(
        visible = bottomBarVisibilityState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                bottomBarItems.forEach {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = ""
                            )
                        },
                        onClick = { navController.navigate(it.route) },
                        selected = false
                    )
                }
            }
        }
    )
}

