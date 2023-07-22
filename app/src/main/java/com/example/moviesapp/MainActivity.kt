package com.example.moviesapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.moviesapp.model.CategoryMovie
import com.example.myapplication.screen.mainScreen.MainScreen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
            MainScreen()



//            AsyncImage(
//                model = "https://example.com/image.jpg",
//                contentDescription = "Translated description of what the image contains"
//            )

//            val db = Firebase.firestore
//            val movieDocumentId = "7Idb8unSOa5sCUedlBFn"
//            val movieRef = db.collection("movie").document(movieDocumentId)
//            movieRef.get()
//                .addOnSuccessListener { movieDocument ->
//                    val categoryId = movieDocument.data!!.get("categoryId").toString()
//
//                    Log.d("XXX", "data =" + movieDocument.data.toString() + " " + categoryId)
//                    val categoryRef = db.collection("category").document(categoryId)
//                    categoryRef.get()
//                        .addOnSuccessListener { categoryDocument ->
//                            if (categoryDocument != null && categoryDocument.exists()) {
//                                Log.d("XXX1", "DocumentSnapshot data: ${categoryDocument.data}")
//                            } else {
//                                Log.d("XXX1", "No such document in category table")
//                            }
//                        }
//                        .addOnFailureListener { exception ->
//                            Log.d("XXX1", "get category failed with ", exception)
//                        }
//                }
//                .addOnFailureListener { exception ->
//                    Log.d("XXX", "get movie failed with ", exception)
//                }

//            val db = Firebase.firestore
//
//            db.collection("movie")
//                .get()
//                .addOnSuccessListener { result ->
//                    for (movieDocument in result) {
//                        val categoryId = movieDocument.data!!.get("categoryId").toString()
//                        val categoryRef = db.collection("category").document(categoryId)
//                        categoryRef.get()
//                            .addOnSuccessListener { categoryDocument ->
//                                Log.d("XXX1", "DocumentSnapshot data: ${categoryDocument.data}")
//                            }
//                                Log.d("XXX", "${movieDocument.id} => ${movieDocument.data}" + " " + categoryId)
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.d("XXX", "Error getting documents: ", exception)
//                }
        }
    }



}






