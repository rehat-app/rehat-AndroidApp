package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)