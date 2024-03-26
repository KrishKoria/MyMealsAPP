package com.example.mymealsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)

class MainViewModel : ViewModel() {
    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState = _categoryState

    private fun getCategories() {
        viewModelScope.launch {
            try {
                val response = service?.getCategories()
                if (response != null) {
                    _categoryState.value = _categoryState.value.copy(
                        list = response.categories,
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    error = "Error Getting Data : ${e.message}",
                    loading = false
                )
            }
        }
    }
    init {
        getCategories()
    }
}



