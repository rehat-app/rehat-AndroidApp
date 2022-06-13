package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("message")
    val message: String
)