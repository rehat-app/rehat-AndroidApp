package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("rescode")
    val rescode: Int,
    @SerializedName("message")
    val message: String
)