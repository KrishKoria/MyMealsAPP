package com.example.mymealsapp

sealed class ScreenRoutes(val route: String) {
    object RecipeScreen : ScreenRoutes("recipe_screen")
    object CategoryDetailScreen : ScreenRoutes("category_detail_screen")
}