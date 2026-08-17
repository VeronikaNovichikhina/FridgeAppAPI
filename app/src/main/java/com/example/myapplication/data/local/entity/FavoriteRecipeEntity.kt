package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites")
data class FavoriteRecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val readyInMinutes: Int,
    val servings: Int,
    val savedAt: Long = System.currentTimeMillis(),
)