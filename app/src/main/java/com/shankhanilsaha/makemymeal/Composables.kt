package com.shankhanilsaha.makemymeal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shankhanilsaha.makemymeal.naviagtion.Screen
import java.nio.file.WatchEvent


@Composable
fun CategoryScreen(modifier: Modifier = Modifier, viewModel: MainViewModel, navController: NavController){
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Categories",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(32.dp)
        )
        
        when {
            viewModel.uiState.value.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center ,horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text("Loading")
                    }
                }
            }
            viewModel.uiState.value.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Error: ${viewModel.uiState.value.errorMessage}",
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            else -> {
                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(viewModel.uiState.value.categories) { category ->
                        CategoryCard(
                            modifier = Modifier.padding(8.dp), category = category,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(modifier: Modifier = Modifier, category: Categories,navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable(enabled = true, onClick = {
                navController.navigate(Screen.CategoryDescriptionScreen.route + "/${category.idCategory}")
            }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = category.strCategoryThumb,
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun CategoryDescriptionScreen(modifier: Modifier, item: Categories, navController: NavController) {
    Column(modifier= Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {navController.navigate(Screen.CategoryScreen.route)}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = item.strCategory,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }
        AsyncImage(
            model = item.strCategoryThumb,
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState())) {
            Text(
                text = item.strCategoryDescription,
                textAlign = TextAlign.Justify
            )
        }
    }
}
