package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Ingredient
import com.example.myapplication.domain.repositories.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val repo: IngredientRepository,
) {
    operator fun invoke(): Flow<List<Ingredient>> = repo.getAll()
}


