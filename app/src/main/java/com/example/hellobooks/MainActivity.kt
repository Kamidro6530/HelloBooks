package com.example.hellobooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hellobooks.mvvm.BookViewModel
import com.example.hellobooks.navigation.Navigation
import com.example.hellobooks.navigation.bottom_nav_bar.AppBottomNavigation
import com.example.hellobooks.ui.theme.HelloBooksTheme
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DynamicColors.applyToActivitiesIfAvailable(this.application)
        val viewModel : BookViewModel by viewModels<BookViewModel>()

        setContent {
            //State of Bottom Bar , set state to false if you want hide bottom bar on screen
            val bottomBarVisibilityState = rememberSaveable { (mutableStateOf(true)) }
            HelloBooksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    androidx.compose.material.Scaffold(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        bottomBar = {
                            AppBottomNavigation(
                                navController = navController,
                                bottomBarVisibilityState = bottomBarVisibilityState
                            )
                        },
                        content = {
                            Column(modifier = Modifier.padding(it)) {
                                Navigation(
                                    navController = navController, bottomBarVisibilityState = bottomBarVisibilityState)
                            }
                        })
                }
            }
        }
    }
}

