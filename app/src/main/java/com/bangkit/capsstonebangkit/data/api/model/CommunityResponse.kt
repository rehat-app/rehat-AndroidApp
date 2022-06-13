package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityResponse(
    @SerializedName("communities")
    val communities: List<Community>,
    @SerializedName("rescode")
    val rescode: String
) {
    data class Community(
        @SerializedName("id")
        val id: Int,
        @SerializedName("id_user")
        val idUser: Int,
        @SerializedName("user_role")
        val userRole: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("hue")
        val hue: Int?,
        @SerializedName("saturation")
        val saturation: String?,
        @SerializedName("light")
        val light: String?,
        @SerializedName("opacity")
        val opacity: Int?
    )
}