package com.example.myapplication.presentation.ingredients

import com.example.myapplication.domain.model.Ingredient

data class IngredientsUiState(
    val ingredients: List<Ingredient> = emptyList(),
    val inputText: String = "",
    val isAddDialogVisible: Boolean = false
)
