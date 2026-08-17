package com.example.myapplication.presentation.ingredients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.SpoonacularApi
import com.example.myapplication.domain.model.Ingredient
import com.example.myapplication.domain.repositories.IngredientRepository
import com.example.myapplication.domain.usecase.AddIngredientUseCase
import com.example.myapplication.domain.usecase.DeleteIngredientUseCase
import com.example.myapplication.domain.usecase.GetIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private  val getIngredients: GetIngredientsUseCase,
    private  val addIngredient: AddIngredientUseCase,
    private val deleteIngredient: DeleteIngredientUseCase
): ViewModel(){

    private val _state = MutableStateFlow(IngredientsUiState())
    val state : StateFlow<IngredientsUiState> = _state.asStateFlow()

    init{
        viewModelScope.launch {
            getIngredients().collect { list ->
                _state.update { it.copy(ingredients = list) }
            }
        }
    }

    fun onInputChange(text: String) = _state.update {
        it.copy(inputText = text)
    }

    fun showAddDialog() = _state.update {
        it.copy(
            isAddDialogVisible = true
        )
    }

    fun hideAddDialog() = _state.update {
        it.copy(
            isAddDialogVisible = false,
            inputText = ""
        )
    }

    fun addIngredient(){
        val name = _state.value.inputText.trim()
        if(name.isEmpty()) return
        viewModelScope.launch {
            addIngredient(Ingredient(name = name))
            hideAddDialog()
        }
    }

    fun deleteIngredient(ingredient: Ingredient){
        viewModelScope.launch {
            deleteIngredient.invoke(ingredient)
        }
    }

}