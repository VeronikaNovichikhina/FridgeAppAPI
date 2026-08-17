package com.example.myapplication.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.UiState
import com.example.myapplication.domain.model.Recipe
import com.example.myapplication.domain.usecase.FindRecipesUseCase
import com.example.myapplication.domain.usecase.GetIngredientsUseCase
import com.example.myapplication.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val findRecipes: FindRecipesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
): ViewModel(){

    private val _state = MutableStateFlow<UiState<List<Recipe>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Recipe>>> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val ingredients = getIngredientsUseCase().first()
            if (ingredients.isEmpty()) {
                _state.value = UiState.Error("Добавь продукты в холодильник")
                return@launch
            }
            _state.value = UiState.Loading
            findRecipes(ingredients).fold(
                onSuccess = { _state.value = UiState.Success(it) },
                onFailure = { _state.value = UiState.Error(it.message ?: "Ошибка загрузки") },
            )
        }
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            toggleFavorite.invoke(recipe)
            // Обновляем локально без перезагрузки
            val current = (_state.value as? UiState.Success)?.data ?: return@launch
            _state.value = UiState.Success(current.map {
                if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
            })
        }
    }

}