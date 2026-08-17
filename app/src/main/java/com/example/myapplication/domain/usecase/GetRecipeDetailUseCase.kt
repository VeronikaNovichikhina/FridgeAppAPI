package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.RecipeDetail
import com.example.myapplication.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repo: RecipeRepository,
) {
    suspend operator fun invoke(id: Int): Result<RecipeDetail> = repo.getRecipeDetail(id)
}