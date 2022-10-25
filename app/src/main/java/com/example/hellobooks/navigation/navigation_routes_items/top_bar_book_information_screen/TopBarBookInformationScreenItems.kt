package com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen

import com.example.hellobooks.R
import com.example.hellobooks.navigation.Routes

open class TopBarBookInformationScreenItems(
    var route: Routes,
    val icon: Int,
    val screenType : ScreenType
    ) {
    object InfoBookInformationScreen : TopBarBookInformationScreenItems(Routes.BookInformationScreen, R.drawable.info_24,ScreenType.Information)
    object ManageBookInformationScreen : TopBarBookInformationScreenItems(Routes.BookInformationScreen, R.drawable.manage_24,ScreenType.Manage)
    object StatsBookInformationScreen : TopBarBookInformationScreenItems(Routes.BookInformationScreen, R.drawable.stats_24,ScreenType.Statistics)


}