package com.example.hellobooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.hellobooks.navigation.Navigation
import com.example.hellobooks.navigation.bottom_nav_bar.AppBottomNavigation
import com.example.hellobooks.navigation.bottom_nav_bar.BottomBarItems
import com.example.hellobooks.ui.theme.HelloBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        setContent {

            HelloBooksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val bottomBarItems = listOf(
                        BottomBarItems.BookShelfScreen,
                        BottomBarItems.FindBookScreen,
                        BottomBarItems.AddNewBookScreen,
                        BottomBarItems.WishListScreen,
                        BottomBarItems.StatisticsScreen
                    )
                    androidx.compose.material.Scaffold(backgroundColor = MaterialTheme.colorScheme.background, bottomBar = {
                        AppBottomNavigation(
                            navController,
                            bottomBarItems
                        )
                    }, content = {
                        Column(modifier = Modifier.padding(it)) {
                            Navigation(
                                navController = navController
                            )
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloBooksTheme {
        Greeting("Android")
    }
}