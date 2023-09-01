package com.example.moviesapp.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FavouriteMoviesModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    val favouriteCollection = firestore.collection("favourite")

    private val _favouriteMovies = MutableStateFlow(emptyList<Movie>())
    val favouriteMovies: StateFlow<List<Movie>> = _favouriteMovies

    private var currentUserId: String? = firebaseAuth.currentUser.toString()

    init {
        resetModel()
        firebaseAuth.addAuthStateListener { auth ->
            if (auth.currentUser != null) {
                currentUserId = auth.currentUser?.uid
                currentUserId?.let { checkNewUser(it) }
            }
            else
                currentUserId = null
            resetModel()
        }
    }

    private fun resetModel() {
        _favouriteMovies.value = emptyList()
        if (currentUserId != null) {
            viewModelScope.launch {
                _favouriteMovies.value = getAllFMovies(currentUserId!!)
            }
        }
    }

    private suspend fun getAllFMovies(userId: String): List<Movie> {
        val documents = favouriteCollection.get().await()
        val movieIds: List<String> = documents.firstOrNull {
            (it.get("user") as? String) == userId
        }?.get("movies") as? List<String> ?: emptyList()

        if (movieIds.isNotEmpty()) {
            var movies: MutableList<Movie> = mutableListOf<Movie>()
            for (movieId in movieIds) {
                val movieDocument =
                    firestore.collection("movies").document(movieId).get().await()

                if (movieDocument.exists()) {
                    val movie = movieDocument.toObject<Movie>()
                    movie!!.id = movieDocument.id.toString()
                    movies.add(movie)
                }
            }
            return movies
        }
        return emptyList()
    }

    private fun checkNewUser(userId: String) {
        favouriteCollection.whereEqualTo("user", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    val newDocument = hashMapOf(
                        "user" to userId,
                        "movies" to emptyList<String>()
                    )

                    favouriteCollection.add(newDocument)
                        .addOnSuccessListener { documentReference ->
                            val newDocumentId = documentReference.id
                            // Tiếp tục xử lý
                        }
                        .addOnFailureListener { e ->
                            // Xử lý lỗi
                        }
                }
            }
            .addOnFailureListener { e ->
                // Xử lý lỗi
            }
    }

    suspend fun updateFavourite(movie: Movie) {
        val currentMovies = _favouriteMovies.value.toMutableList()

        if (currentMovies.contains(movie)) {
            currentMovies.remove(movie)
        } else {
            currentMovies.add(movie)
        }

        _favouriteMovies.value = currentMovies
        currentUserId?.let { updateFavouriteMoviesOnFirebase(it) }
    }

    private suspend fun updateFavouriteMoviesOnFirebase(currentUserId: String) {
        val moviesToUpdate = _favouriteMovies.value.map { it.id }

        favouriteCollection
            .whereEqualTo("user", currentUserId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents.first()

                    val updateData = hashMapOf(
                        "user" to currentUserId,
                        "movies" to moviesToUpdate
                    )

                    document.reference
                        .update(updateData)
                        .addOnSuccessListener {
                        }
                        .addOnFailureListener { e ->
                        }
                }
            }
            .addOnFailureListener { e ->
                // Xử lý lỗi
            }
            .await()
    }

}
