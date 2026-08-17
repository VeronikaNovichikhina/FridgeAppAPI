package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface  RecipeRepository {

    suspend fun findRecipesByIngredients(ingredients: List<String>): Result<List<Recipe>>

    suspend fun getRecipeDetail(id: Int): Result<RecipeDetail>

    fun getFavorites(): Flow<List<Recipe>>

    suspend fun toggleFavorite(recipe: Recipe)

    suspend fun getShoppingList(recipeId: Int): Result<List<String>>

}