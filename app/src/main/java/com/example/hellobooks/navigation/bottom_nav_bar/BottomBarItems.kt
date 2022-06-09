package com.example.hellobooks.navigation.bottom_nav_bar

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hellobooks.R

sealed class BottomBarItems(
    var route: String,
    val icon: ImageVector,

    ) {
    object BookShelfScreen : BottomBarItems("bookShelfScreen",R.drawable.bookshelf as ImageVector)
    object FindBookScreen : BottomBarItems("findBookScreen",Icons.Filled.Search)
    object WishListScreen : BottomBarItems("bookShelfScreen",R.drawable.bookshelf as ImageVector)
}
