package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class ForgetPasswordRequest(
    @SerializedName("email")
    val email: String
)