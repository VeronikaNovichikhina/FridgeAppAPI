package com.example.myapplication.domain.model

data class RecipeDetail(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val instructions: List<Step>,
    val extendedIngredients: List<DetailIngredient>
)