package com.shankhanilsaha.makemymeal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    data class CategoryFetchStatus(
        var isLoading: Boolean = true,
        var errorMessage: String? = null,
        var categories: List<Categories> = emptyList()
    )
    init {
        fetchCategories()
    }
    private val _uiState = mutableStateOf(CategoryFetchStatus())
    val uiState: MutableState<CategoryFetchStatus> = _uiState
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = ApiSetup.retrofitServices.getCategories()
                _uiState.value = _uiState.value.copy(isLoading = false,
                    categories = response.categories)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false,
                    errorMessage = e.message)
                println(e.message)
            }
        }
    }
}