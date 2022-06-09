package com.example.hellobooks.navigation.bottom_nav_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AppBottomNavigation(navController: NavHostController, items: List<BottomBarItems>) {
    BottomNavigation {
        items.forEach {
            BottomNavigationItem(icon = { it.icon },
                onClick = { navController.navigate(it.route) },
                selected = false
            )
        }
    }
}