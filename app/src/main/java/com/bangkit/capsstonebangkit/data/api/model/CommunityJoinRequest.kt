package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityJoinRequest(
    @SerializedName("token")
    val token: String
)