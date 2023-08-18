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

    fun levenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

        for (i in 0..s1.length) {
            for (j in 0..s2.length) {
                if (i == 0) {
                    dp[i][j] = j
                } else if (j == 0) {
                    dp[i][j] = i
                } else {
                    dp[i][j] = minOf(
                        dp[i - 1][j - 1] + costOfSubstitution(s1[i - 1], s2[j - 1]),
                        dp[i][j - 1] + 1,
                        dp[i - 1][j] + 1
                    )
                }
            }
        }

        return dp[s1.length][s2.length]
    }

    fun costOfSubstitution(a: Char, b: Char): Int {
        return if (a == b) 0 else 1
    }

}