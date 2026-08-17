package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.core.Screen
import com.example.myapplication.presentation.detail.DetailScreen
import com.example.myapplication.presentation.favorite.FavoriteScreen
import com.example.myapplication.presentation.ingredients.IngredientScreen
import com.example.myapplication.presentation.recipe.RecipesScreen
import com.example.myapplication.presentation.shopping.ShoppingScreen

// Экраны где показывать нижнюю панель
private val bottomNavScreens = listOf(
    Screen.Ingredients.route,
    Screen.Recipe.route,
    Screen.Favorite.route,
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Показываем нижнюю панель только на главных экранах
            if (currentRoute in bottomNavScreens) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Screen.Ingredients.route,
                        onClick = {
                            navController.navigate(Screen.Ingredients.route) {
                                popUpTo(Screen.Ingredients.route) { inclusive = true }
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Холодильник") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Recipe.route,
                        onClick = {
                            navController.navigate(Screen.Recipe.route) {
                                //очищаем стек жо этого экрана,Без этого каждый тап добавлял бы новую копию экрана в стек и кнопка назад вела бы через все копии
                                popUpTo(Screen.Ingredients.route)
                            }
                        },
                        icon = { Icon(Icons.Default.Search, contentDescription = null) },
                        label = { Text("Рецепты") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Favorite.route,
                        onClick = {
                            navController.navigate(Screen.Favorite.route) {
                                popUpTo(Screen.Ingredients.route)
                            }
                        },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text("Избранное") }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Ingredients.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Screen.Ingredients.route) {
                IngredientScreen(
                    onFindRecipes = { navController.navigate(Screen.Recipe.route) },
                )
            }

            composable(Screen.Recipe.route) {
                RecipesScreen(
                    onRecipeClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    },
                )
            }

            composable(
                route = Screen.Detail.route, //  /detail/{recipeId} = /detail/42
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType }),
            ) {
                DetailScreen(
                    onBack = { navController.popBackStack() },
                    onShoppingList = { id ->
                        navController.navigate(Screen.ShoppingList.createRoute(id))
                    },
                )
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    onRecipeClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    },
                )
            }

            composable(
                route = Screen.ShoppingList.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType }),
            ) {
                ShoppingScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}