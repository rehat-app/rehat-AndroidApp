package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityCreateRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)