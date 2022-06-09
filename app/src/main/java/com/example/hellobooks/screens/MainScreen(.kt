package com.example.hellobooks.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.hellobooks.navigation.bottom_nav_bar.AppBottomNavigation
import com.example.hellobooks.navigation.bottom_nav_bar.BottomBarItems

@Composable
fun MainScreen() {
    
    var navController = rememberNavController()
    
    val bottomBarItems = listOf(
        BottomBarItems.BookShelfScreen,
        BottomBarItems.FindBookScreen,
        BottomBarItems.AddNewBookScreen,
        BottomBarItems.WishListScreen,
        BottomBarItems.StatisticsScreen
    )
    Scaffold(bottomBar = {AppBottomNavigation(navController,bottomBarItems)}) {

    }
    
}