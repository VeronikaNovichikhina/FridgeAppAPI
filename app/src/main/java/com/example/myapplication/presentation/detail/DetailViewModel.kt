package com.example.myapplication.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.UiState
import com.example.myapplication.domain.model.RecipeDetail
import com.example.myapplication.domain.usecase.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipeDetail: GetRecipeDetailUseCase,
) : ViewModel() {

    private val recipeId: Int = checkNotNull(savedStateHandle["recipeId"])

    private val _state = MutableStateFlow<UiState<RecipeDetail>>(UiState.Loading)
    val state: StateFlow<UiState<RecipeDetail>> = _state.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    init {
        viewModelScope.launch {
            getRecipeDetail(recipeId).fold(
                onSuccess = { _state.value = UiState.Success(it) },
                onFailure = { _state.value = UiState.Error(it.message ?: "Ошибка") },
            )
        }
    }

    fun nextStep(total: Int) { if (_currentStep.value < total - 1) _currentStep.value++ }
    fun prevStep() { if (_currentStep.value > 0) _currentStep.value-- }
}
