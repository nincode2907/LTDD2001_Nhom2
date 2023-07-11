package com.example.myapplication.screen.mainScreen

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.NavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> get() = _uiState

    init {
        _uiState.value = MainUiState(arrayListOf(NavigationItem.Home,NavigationItem.Search,NavigationItem.ComingSoon,NavigationItem.Ranking))
    }
}