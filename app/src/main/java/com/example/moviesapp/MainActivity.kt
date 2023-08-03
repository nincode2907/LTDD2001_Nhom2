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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.moviesapp.model.CategoryMovie
import com.example.myapplication.screen.mainScreen.MainScreen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
            MainScreen()

            val db = Firebase.firestore

            db.collection("cities")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("XXX", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("XXX", "Error getting documents: ", exception)
                }
        }
    }
}
@Preview(showBackground = true, widthDp = 564, heightDp = 1254)
@Composable
fun PreviewMessageCard() {
    MainScreen()
}
