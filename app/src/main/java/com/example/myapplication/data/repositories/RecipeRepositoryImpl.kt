package com.example.myapplication.data.repositories

import com.example.myapplication.data.local.dao.FavoriteRecipeDao
import com.example.myapplication.data.local.entity.FavoriteRecipeEntity
import com.example.myapplication.data.remote.SpoonacularApi
import com.example.myapplication.domain.model.DetailIngredient
import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.model.RecipeDetail
import com.example.myapplication.domain.model.Step
import com.example.myapplication.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dao: FavoriteRecipeDao,
    private val api: SpoonacularApi
): RecipeRepository {

    override suspend fun findRecipesByIngredients(ingredients: List<String>) : Result<List<Recipe>> =
        runCatching {
            val query = ingredients.joinToString(",")
            api.findByIngredients(query).map { dto ->
                Recipe(
                    id = dto.id,
                    title = dto.title,
                    image = dto.image,
                    usedIngredientCount = dto.usedIngredientCount,
                    missedIngredientCount = dto.missedIngredientCount,
                    missedIngredients = dto.missedIngredients.map { it.name },
                    isFavorite = dao.isFavorite(dto.id)
                )
            }
        }

    override suspend fun getRecipeDetail(id: Int): Result<RecipeDetail> =
        runCatching {
            val dto = api.getRecipeInfo(id)
            RecipeDetail(
                id = dto.id,
                title = dto.title,
                image = dto.image,
                readyInMinutes = dto.readyInMinutes,
                servings = dto.servings,
                summary = dto.summary.stripHtml(),
                instructions = dto.analyzedInstructions
                    .firstOrNull()?.steps
                    ?.map { Step(it.number, it.step, it.length?.number) }
                    ?: emptyList(),
                extendedIngredients = dto.extendedIngredients.map {
                    DetailIngredient(it.name, it.amount, it.unit, it.image)
                },
            )
        }


    override fun getFavorites(): Flow<List<Recipe>> =
        dao.getAll().map { list ->
            list.map {
                Recipe(
                    it.id,
                    it.title,
                    it.image,
                    it.usedIngredientCount,
                    it.missedIngredientCount,
                    readyInMinutes = it.readyInMinutes,
                    servings = it.servings,
                    isFavorite = true
                )
            }
        }
    override suspend fun toggleFavorite(recipe: Recipe) {
        if (dao.isFavorite(recipe.id)) {
            dao.delete(recipe.id)
        } else {
            dao.insert(
                FavoriteRecipeEntity(
                    id = recipe.id,
                    title = recipe.title,
                    image = recipe.image,
                    usedIngredientCount = recipe.usedIngredientCount,
                    missedIngredientCount = recipe.missedIngredientCount,
                    readyInMinutes = recipe.readyInMinutes,
                    servings = recipe.servings
                )
            )
        }
    }
    override suspend fun getShoppingList(recipeId: Int): Result<List<String>> =
        runCatching {
            api.getIngredients(recipeId).ingredients.map { item ->
                val metric = item.amount.metric
                "${item.name} — ${metric.value} ${metric.unit}"
            }
        }

    private fun String.stripHtml(): String =
        replace(Regex("<[^>]*>"), "").replace("&amp;", "&").trim()
}