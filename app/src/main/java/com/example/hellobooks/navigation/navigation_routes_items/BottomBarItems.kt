package com.example.hellobooks.navigation.navigation_routes_items

import com.example.hellobooks.R

open class BottomBarItems(
    var route: String,
    val icon: Int,

    ) {
    object BookShelfScreen : BottomBarItems("bookShelfScreen",R.drawable.books_24)
    object FindBookScreen : BottomBarItems("findBookScreen",R.drawable.search_24)
    object WishListScreen : BottomBarItems("wishListScreen",R.drawable.listcheck_24)
    object AddNewBookScreen : BottomBarItems("addNewBookScreen" + "/{book}",R.drawable.add_24)
    object StatisticsScreen : BottomBarItems("statisticsScreen",R.drawable.charthistogram_24)
}


