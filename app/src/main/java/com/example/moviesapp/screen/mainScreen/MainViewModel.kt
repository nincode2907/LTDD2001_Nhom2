package com.example.myapplication.screen.mainScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> get() = _uiState

    init {
        _uiState.value = MainUiState(arrayListOf(NavigationItem.Home,NavigationItem.Search,NavigationItem.ComingSoon,NavigationItem.Ranking,NavigationItem.User))
    }
}