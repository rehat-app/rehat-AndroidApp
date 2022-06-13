package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String
)