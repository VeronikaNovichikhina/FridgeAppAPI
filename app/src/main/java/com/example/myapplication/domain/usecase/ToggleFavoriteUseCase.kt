package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.repositories.RecipeRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repo: RecipeRepository,
) {
    suspend operator fun invoke(recipe: Recipe) = repo.toggleFavorite(recipe)
}