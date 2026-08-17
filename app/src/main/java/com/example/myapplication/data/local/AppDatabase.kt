package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.FavoriteRecipeDao
import com.example.myapplication.data.local.dao.IngredientDao
import com.example.myapplication.data.local.entity.FavoriteRecipeEntity
import com.example.myapplication.data.local.entity.IngredientEntity

@Database(entities = [IngredientEntity::class, FavoriteRecipeEntity::class], version = 2 , exportSchema = false)
abstract  class AppDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
}
