package com.example.moviesapp.data.moviesData

import android.content.ContentValues
import android.util.Log
import com.example.moviesapp.model.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor( var db: FirebaseFirestore) {
    suspend fun getAllMovies(): List<Movie> {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        val result = db.collection("movies").get().await()
        for (document in result) {
            val movie = document.toObject<Movie>()
            val date = document.getDate("releaseDate")

            movies.add(movie)
        }
        return movies
    }

    suspend fun getMoviesPoster(): List<Movie> {
        var movies: MutableList<Movie> = mutableListOf<Movie>()
        val result = db.collection("movies").whereEqualTo("outstanding",true).get().await()
        for (document in result) {
            val movie = document.toObject<Movie>()
            movies.add(movie)
        }
        return movies
    }

    suspend fun addMovie(movie: Movie) {
        val collection = db.collection("movies")
        collection.add(movie).await()
    }


}






//val date = Date(2002, 5, 11)
//            val db = Firebase.firestore
//            val movie = Movie(
//                name = "Doraemon: Nobita Và Cuộc Chiến Vũ Trụ Tí Hon",
//                description = "Nobita tình cờ gặp được người ngoài hành tinh tí hon Papi, vốn là Tổng thống của hành tinh Pirika, chạy trốn tới Trái Đất để thoát khỏi những kẻ nổi loạn nơi quê nhà. Doraemon, Nobita và hội bạn thân dùng bảo bối đèn pin thu nhỏ biến đổi theo kích cỡ giống Papi để chơi cùng cậu bé. Thế nhưng, một tàu chiến không gian tấn công cả nhóm. Cảm thấy có trách nhiệm vì liên lụy mọi người, Papi quyết định một mình đương đầu với quân phiến loạn tàn ác. Doraemon và các bạn lên đường đến hành tinh Pirika, sát cánh bên người bạn của mình.",
//                trailer = "dd_R1GQwKlY",
//                image = "https://static.wikia.nocookie.net/dorepedia/images/8/8d/Doraemon_the_Movie_41_poster.jpg/revision/latest?cb=20220506110934&path-prefix=vi",
//                releaseDate = Timestamp(date),
//                episodeTotal = "Trọn bộ",
//                time = "108 phút",
//                country = "Nhật Bản",
//                view = 1,
//                outstanding = false,
//                category = listOf(
//                    "Anime",
//                    "Phiêu Lưu",
//                    "Giả Tưởng",
//                ),
//                linkUriMovie = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
//            )
//
//            db.collection("movies")
//                .add(movie)
//                .addOnSuccessListener { documentReference ->
//                }
//                .addOnFailureListener { e ->
//                }
