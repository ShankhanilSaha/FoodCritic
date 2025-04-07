package com.shankhanilsaha.makemymeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.shankhanilsaha.makemymeal.naviagtion.RecipeApp
import com.shankhanilsaha.makemymeal.ui.theme.MakeMyMealTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val navController = rememberNavController()
            MakeMyMealTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeApp(modifier = Modifier.padding(innerPadding), navController = navController)
                }
            }
        }
    }
}