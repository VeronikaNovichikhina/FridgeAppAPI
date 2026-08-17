package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(
    private val repo: RecipeRepository,
) {
    suspend operator fun invoke(recipeId: Int): Result<List<String>> =
        repo.getShoppingList(recipeId)
}