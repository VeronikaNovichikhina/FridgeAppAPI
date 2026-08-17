package com.example.myapplication.data.remote.dto

data class RecipeDetailDto(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val analyzedInstructions: List<InstructionDto>,
    val extendedIngredients: List<ExtendedIngredientDto>
)
