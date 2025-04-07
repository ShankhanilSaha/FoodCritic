package com.shankhanilsaha.makemymeal.naviagtion

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shankhanilsaha.makemymeal.CategoryDescriptionScreen
import com.shankhanilsaha.makemymeal.CategoryScreen
import com.shankhanilsaha.makemymeal.MainViewModel


@Composable
fun RecipeApp(modifier: Modifier,navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.CategoryScreen.route) {
        composable(Screen.CategoryScreen.route) {
            CategoryScreen(viewModel = viewModel,
                modifier = Modifier.fillMaxSize(),
                navController = navController)
        }
        composable(Screen.CategoryDescriptionScreen.route + "/{idCategory}") {
            val idCategory = it.arguments?.getString("idCategory")
            if (idCategory != null) {
                val categoryItem =
                    viewModel.uiState.value.categories.find { it.idCategory == idCategory }
                if (categoryItem != null) {
                    CategoryDescriptionScreen(
                        item = categoryItem,
                        modifier = Modifier.fillMaxSize(),
                        navController = navController
                    )
                }
            }
        }
    }
}