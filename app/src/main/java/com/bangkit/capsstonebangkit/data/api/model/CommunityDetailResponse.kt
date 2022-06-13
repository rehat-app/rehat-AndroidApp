package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityDetailResponse(
    @SerializedName("community")
    val community: List<Community>,
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("colors")
    val colors: List<Color>,
    @SerializedName("rescode")
    val rescode: String
) {
    data class Community(
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("user_role")
        val userRole: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("createDate")
        val createDate: String
    )

    data class Member(
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("user_role")
        val userRole: String
    )

    data class Color(
        @SerializedName("hue")
        val hue: Int,
        @SerializedName("saturation")
        val saturation: String,
        @SerializedName("light")
        val light: String,
        @SerializedName("opacity")
        val opacity: Int
    )
}