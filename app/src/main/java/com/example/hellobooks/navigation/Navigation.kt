package com.example.hellobooks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellobooks.screens.*


@Composable
fun Navigation(navController: NavHostController, bottomBarVisibilityState: MutableState<Boolean>) {

        NavHost(
            navController = navController,
            startDestination = Routes.BookShelfScreen.route
        ) {

            composable(Routes.BookShelfScreen.route) {
                bottomBarVisibilityState.value = true
                BookShelfScreen()
            }
            composable(Routes.AddNewBookScreen.route) {
                bottomBarVisibilityState.value = false
                AddNewBookScreen()
            }
            composable(Routes.FindBookScreen.route) {
                bottomBarVisibilityState.value = true
                FindBookScreen()
            }
            composable(Routes.StatisticsScreen.route) {
                bottomBarVisibilityState.value = true
                StatisticsScreen()
            }
            composable(Routes.WishListScreen.route) {
                bottomBarVisibilityState.value = true
                WishListScreen()
            }


        }
}