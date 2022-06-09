package com.example.hellobooks.navigation

sealed class Routes(var route : String){
    object AddNewBookScreen : Routes("addNewBookScreen")
    object BookShelfScreen : Routes("bookShelfScreen")
    object FindBookScreen : Routes("findBookScreen")
    object StatisticsScreen : Routes("statisticsScreen")
    object WishListScreen : Routes("wishListScreen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
