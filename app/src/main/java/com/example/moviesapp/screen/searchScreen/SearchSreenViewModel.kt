package com.example.moviesapp.screen.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.categoriesData.CategoriesRepository
import com.example.moviesapp.model.CategoryMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSreenViewModel @Inject  constructor ( val categoryRepository : CategoriesRepository) : ViewModel() {
    private val _categories = MutableStateFlow<List<CategoryMovie>>(emptyList())
    val categories: StateFlow<List<CategoryMovie>> get() = _categories


    init {
        getAllMovies()
    }
    private fun getAllMovies() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getAllCategory()
        }
    }

}