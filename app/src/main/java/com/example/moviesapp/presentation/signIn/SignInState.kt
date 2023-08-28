package com.example.moviesapp.presentation.signIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)