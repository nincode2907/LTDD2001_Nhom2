package com.example.moviesapp.screen.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.moviesData.MovieRepository
import com.example.moviesapp.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var movieRepository: MovieRepository) :
    ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    init {
        getAllMovies()
    }
    private fun getAllMovies() {
        viewModelScope.launch {
            _movies.value = movieRepository.getAllMovies()
        }
    }
}