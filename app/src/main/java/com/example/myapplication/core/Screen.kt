package com.example.myapplication.core

sealed class Screen(val route: String){
    object Ingredients: Screen("ingredients")
    object Recipe: Screen("recipe")
    object Favorite: Screen("favorite")
    object Detail: Screen("detail/{recipeId}"){
        fun createRoute(id: Int) = "detail/$id"
    }
    object ShoppingList: Screen("shoppingList/{recipeId}"){
        fun createRoute(id: Int) = "shoppingList/$id"
    }
}