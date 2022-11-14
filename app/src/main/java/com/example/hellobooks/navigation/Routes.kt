package com.example.hellobooks.navigation

import com.example.hellobooks.navigation.navigation_routes_items.top_bar_book_information_screen.ScreenType

sealed class Routes(var route : String){
    object AddNewBookScreen : Routes("addNewBookScreen")
    object BookShelfScreen : Routes("bookShelfScreen")
    object FindBookScreen : Routes("findBookScreen")
    object StatisticsScreen : Routes("statisticsScreen")
    object WishListScreen : Routes("wishListScreen")
    object BookInformationScreen : Routes("bookInformationScreen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }


    fun withArgsAndScreenTypeForBookInformationScreen(vararg stringArgs : String?,screenType: ScreenType ) : String{
        return buildString {
            append(route)
            stringArgs.forEach { arg ->
                append("/$arg")
            }
            append("/${screenType.name}")
        }
    }
}
