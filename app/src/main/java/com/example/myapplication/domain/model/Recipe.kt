package com.example.myapplication.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<String> = emptyList(),
    val readyInMinutes: Int = 0,
    val servings: Int = 1,
    val isFavorite: Boolean = false
)