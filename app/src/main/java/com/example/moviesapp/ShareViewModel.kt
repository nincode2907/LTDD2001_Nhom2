package com.example.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviesapp.model.Movie

class ShareViewModel : ViewModel() {
    var movie by mutableStateOf<Movie?>(null)
        private set
}