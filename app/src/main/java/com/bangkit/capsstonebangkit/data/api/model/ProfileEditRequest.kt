package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class ProfileEditRequest(
 @SerializedName("id")
 val id: Int,
 @SerializedName("username")
 val username: String,
 @SerializedName("image")
 val image: String,
)