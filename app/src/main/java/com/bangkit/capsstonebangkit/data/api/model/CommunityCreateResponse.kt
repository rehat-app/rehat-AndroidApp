package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityCreateResponse(
    @SerializedName("rescode")
    val rescode: Int,
    @SerializedName("idCommunity")
    val idCommunity: Int,
    @SerializedName("message")
    val message: String
)