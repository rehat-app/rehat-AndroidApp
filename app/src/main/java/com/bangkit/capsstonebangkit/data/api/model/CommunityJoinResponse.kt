package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityJoinResponse(
    @SerializedName("rescode")
    val rescode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("id_community")
    val idCommunity: Int
)