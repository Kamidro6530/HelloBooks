package com.example.hellobooks.navigation.bottom_nav_bar

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hellobooks.R

open class BottomBarItems(
    var route: String,
    val icon: Int,

    ) {
    object BookShelfScreen : BottomBarItems("bookShelfScreen",R.drawable.books_24)
    object FindBookScreen : BottomBarItems("findBookScreen",R.drawable.search_24)
    object WishListScreen : BottomBarItems("wishListScreen",R.drawable.listcheck_24)
    object AddNewBookScreen : BottomBarItems("addNewBookScreen",R.drawable.add_24)
    object StatisticsScreen : BottomBarItems("statisticsScreen",R.drawable.charthistogram_24)
}


