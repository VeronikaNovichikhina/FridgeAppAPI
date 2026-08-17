package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Ingredient
import com.example.myapplication.domain.repositories.IngredientRepository
import javax.inject.Inject

class AddIngredientUseCase @Inject constructor(
    private val repo: IngredientRepository,
) {
    suspend operator fun invoke(ingredient: Ingredient) = repo.addIngredient(ingredient)
}