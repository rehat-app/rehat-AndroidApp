package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String
)