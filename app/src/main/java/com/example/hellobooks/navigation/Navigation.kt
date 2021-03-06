package com.example.hellobooks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.navArgument

import com.example.hellobooks.screens.*
import com.example.hellobooks.screens.book.BookInformationScreen


@Composable
fun Navigation(navController: NavHostController, bottomBarVisibilityState: MutableState<Boolean>) {

        NavHost(
            navController = navController,
            startDestination = Routes.BookShelfScreen.route
        ) {

            composable(Routes.BookShelfScreen.route) {
                bottomBarVisibilityState.value = true
                BookShelfScreen(navController)
            }
            composable(Routes.AddNewBookScreen.route) {
                bottomBarVisibilityState.value = false
                AddNewBookScreen(navController)
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
            composable(Routes.BookInformationScreen.route + "/{book}",
                arguments = listOf(
                    navArgument("book"){},
                )){ entry ->
                bottomBarVisibilityState.value = false
                BookInformationScreen(
                    jsonBook = entry.arguments?.getString("book"),
                    )
            }


        }
}