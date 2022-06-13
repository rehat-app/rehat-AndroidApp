package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("password")
    val password: String
)