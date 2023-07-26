package com.example.moviesapp.data

import android.content.ContentValues
import android.util.Log
import com.example.moviesapp.model.CategoryMovie
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CategoryRepository() {
    val db = Firebase.firestore
    suspend fun getAllCategories(): List<CategoryMovie> = withContext(Dispatchers.IO) {
        var categories: MutableList<CategoryMovie> = mutableListOf<CategoryMovie>()
        try {
            val result = db.collection("category").get().await()
            for (document in result) {
                val category = CategoryMovie(document.id, document.data["name"].toString(),document.data["image"].toString())
                categories.add(category)
            }

        }catch (exception: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

        categories
    }

    suspend fun getAllCategoriesLimited(limit: Long): List<CategoryMovie> = withContext(Dispatchers.IO) {
        var categories: MutableList<CategoryMovie> = mutableListOf<CategoryMovie>()
        try {
            val result = db.collection("category").limit(limit).get().await()
            for (document in result) {
                val category = CategoryMovie(document.id, document.data["name"].toString(),document.data["image"].toString())
                categories.add(category)
            }
        } catch (exception: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }
        categories
    }
}