package com.example.moviesapp.data.categoriesData

import com.example.moviesapp.model.CategoryMovie
import com.example.moviesapp.model.Movie
import javax.inject.Inject

class CategoriesRepository @Inject constructor(var categoriesRemoteDataSource: CategoriesRemoteDataSource) {


    suspend fun getAllCategory(): List<CategoryMovie> {
        return categoriesRemoteDataSource.getAllCategory()
    }
}