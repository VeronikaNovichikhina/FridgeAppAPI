package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {

    fun getAll(): Flow<List<Ingredient>>

    suspend fun addIngredient(ingredient: Ingredient)

    suspend fun deleteIngredient(ingredient: Ingredient)
}