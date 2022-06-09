package com.example.hellobooks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hellobooks.screens.*

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.BookShelfScreen.route ){

        composable(Routes.BookShelfScreen.route){
            BookShelfScreen()
        }
        composable(Routes.AddNewBookScreen.route){
            AddNewBookScreen()
        }
        composable(Routes.FindBookScreen.route){
            FindBookScreen()
        }
        composable(Routes.StatisticsScreen.route){
            StatisticsScreen()
        }
        composable(Routes.WishListScreen.route){
            WishListScreen()
        }


    }
}