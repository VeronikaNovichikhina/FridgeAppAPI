package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repo: RecipeRepository,
) {
    operator fun invoke(): Flow<List<Recipe>> = repo.getFavorites()
}