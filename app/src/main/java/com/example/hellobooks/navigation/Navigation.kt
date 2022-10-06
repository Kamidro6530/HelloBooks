package com.example.hellobooks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.navArgument

import com.example.hellobooks.screens.*
import com.example.hellobooks.screens.book.BookInformationScreen


@OptIn(ExperimentalComposeUiApi::class)
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
        composable(
            Routes.AddNewBookScreen.route + "/{book}",
            arguments = listOf(
                navArgument("book") {},
            )
        ) { entry ->
            bottomBarVisibilityState.value = false
            AddNewBookScreen(
                navController = navController,
                jsonBook = entry.arguments?.getString("book"))
        }
        composable(Routes.FindBookScreen.route) {
            bottomBarVisibilityState.value = true
            FindBookScreen(navController)
        }
        composable(Routes.StatisticsScreen.route) {
            bottomBarVisibilityState.value = true
            StatisticsScreen()
        }
        composable(Routes.WishListScreen.route) {
            bottomBarVisibilityState.value = true
            WishListScreen(navController)
        }
        composable(
            Routes.BookInformationScreen.route + "/{book}" + "/{route}",
            arguments = listOf(
                navArgument("book") {},
                navArgument("route") {}
            )
        ) { entry ->
            bottomBarVisibilityState.value = false
            BookInformationScreen(
                jsonBook = entry.arguments?.getString("book"),
                navController = navController,
                route = entry.arguments?.getString("route")

            )
        }


    }
}