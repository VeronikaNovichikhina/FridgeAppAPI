package com.example.myapplication.data.repositories

import com.example.myapplication.data.local.dao.IngredientDao
import com.example.myapplication.data.local.entity.IngredientEntity
import com.example.myapplication.domain.model.Ingredient
import com.example.myapplication.domain.repositories.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val dao: IngredientDao
) : IngredientRepository {

    override fun getAll(): Flow<List<Ingredient>> =
        dao.getAll().map { list -> list.map {it.toDomain() } }

    override suspend fun addIngredient(ingredient: Ingredient) =
        dao.insert(ingredient.toEntity())

    override suspend fun deleteIngredient(ingredient: Ingredient) =
        dao.delete(ingredient.toEntity())

    private fun IngredientEntity.toDomain() = Ingredient(id, name, emoji, amount)
    private fun Ingredient.toEntity() = IngredientEntity(id, name, emoji, amount)
}