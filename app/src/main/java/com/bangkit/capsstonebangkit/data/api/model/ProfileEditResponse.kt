package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class ProfileEditResponse(
 @SerializedName("message")
 val message: String,
 @SerializedName("rescode")
 val rescode: Int
)