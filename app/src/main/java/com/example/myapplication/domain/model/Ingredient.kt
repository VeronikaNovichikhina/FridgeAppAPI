package com.example.myapplication.domain.model

data class Ingredient(
    val id: Long = 0,
    val name: String,
    val emoji: String = "",
    val amount: String = ""
)
