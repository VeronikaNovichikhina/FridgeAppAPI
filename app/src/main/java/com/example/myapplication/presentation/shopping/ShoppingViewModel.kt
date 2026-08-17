package com.example.myapplication.presentation.shopping

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.UiState
import com.example.myapplication.domain.usecase.GetShoppingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getShoppingList: GetShoppingListUseCase,
) : ViewModel() {

    private val recipeId: Int = checkNotNull(savedStateHandle["recipeId"])

    private val _state = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val state: StateFlow<UiState<List<String>>> = _state.asStateFlow()

    private val _checked = MutableStateFlow<Set<Int>>(emptySet())
    val checked: StateFlow<Set<Int>> = _checked.asStateFlow()

    init {
        viewModelScope.launch {
            getShoppingList(recipeId).fold(
                onSuccess = { _state.value = UiState.Success(it) },
                onFailure = { _state.value = UiState.Error(it.message ?: "Ошибка") },
            )
        }
    }

    fun toggle(index: Int) {
        _checked.update { if (index in it) it - index else it + index }
    }
}
