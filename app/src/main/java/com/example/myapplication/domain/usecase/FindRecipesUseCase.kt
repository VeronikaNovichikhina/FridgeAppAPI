package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Ingredient
import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.repositories.RecipeRepository
import javax.inject.Inject

class FindRecipesUseCase @Inject constructor(
    private val repo: RecipeRepository,
) {
    // Ищем рецепты, сортируем по числу недостающих ингредиентов (меньше = лучше)
    suspend operator fun invoke(ingredients: List<Ingredient>): Result<List<Recipe>> {
        val names = ingredients.map { it.name }
        return repo.findRecipesByIngredients(names).map { recipes ->
            recipes.sortedBy { it.missedIngredientCount }
        }
    }
}
