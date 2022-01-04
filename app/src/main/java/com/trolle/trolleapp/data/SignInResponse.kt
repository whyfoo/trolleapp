package com.trolle.trolleapp.data

data class SignInResponse (
        val status: String,
        val message: String,
        val data: SignInData
        )

data class SignInData(
        val id: Int,
        val token: String
)