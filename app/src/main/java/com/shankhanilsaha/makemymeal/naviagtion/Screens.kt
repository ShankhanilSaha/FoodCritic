package com.shankhanilsaha.makemymeal.naviagtion

sealed class Screen(val route: String) {
    object CategoryScreen : Screen(route = "category")
    object CategoryDescriptionScreen : Screen(route = "category_description")
}