package com.example.moviesapp.screen.searchScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.CategoryRepository
import com.example.moviesapp.model.CategoryMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchSreenViewModel  constructor ( val categoryRepository: CategoryRepository) : ViewModel() {

//    private val _categories = MutableStateFlow(SearchSreenState())
//    val categories: StateFlow<SearchSreenState> get() = _categories
//
//    init {
//        loadCategories()
//    }
//
//    private fun loadCategories() {
//        viewModelScope.launch {
//            _categories.value.categories = categoryRepository.getAllCategories()
//        }
//    }
}