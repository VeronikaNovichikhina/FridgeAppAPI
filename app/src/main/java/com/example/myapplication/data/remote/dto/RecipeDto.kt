package com.example.myapplication.data.remote.dto

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredientDto>
)