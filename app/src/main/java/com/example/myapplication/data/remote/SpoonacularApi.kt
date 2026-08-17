package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.IngredientsWidgetDto
import com.example.myapplication.data.remote.dto.RecipeDetailDto
import com.example.myapplication.data.remote.dto.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_API = "434c5722c59e4effbe421dea450b084d"

interface SpoonacularApi {


    @GET("recipes/findByIngredients")
    suspend fun findByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = 20,
        @Query("ranking") ranking: Int = 2,
        @Query("ignorePantry") ignorePantry: Boolean = true,
        @Query("apiKey") apiKey: String = BASE_API
    ): List<RecipeDto>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInfo(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") apiKey: String = BASE_API
    ): RecipeDetailDto

    @GET("recipes/{id}/ingredientWidget.json")
    suspend fun getIngredients(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = BASE_API
    ): IngredientsWidgetDto

}