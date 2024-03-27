package com.example.mymealsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainAPP(navController: NavHostController) {
    val mainAppViewModel: MainViewModel = viewModel()
    val viewState by mainAppViewModel.categoryState
    NavHost(navController = navController, startDestination = ScreenRoutes.RecipeScreen.route) {
        composable(ScreenRoutes.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, onNavigateToDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(ScreenRoutes.CategoryDetailScreen.route)
            })
        }
        composable(ScreenRoutes.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}