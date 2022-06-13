package com.bangkit.capsstonebangkit.data.api

import com.bangkit.capsstonebangkit.data.api.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ApiHelper(private val apiService: ApiService) {

    suspend fun postRegister(
        username: RequestBody,
        email: RequestBody,
        password: RequestBody,
        image: MultipartBody.Part
    ) = apiService.postRegister(username, email, password, image)

    suspend fun postLogin(request: LoginRequest) = apiService.postLogin(request)

    suspend fun postForgetPassword(request: ForgetPasswordRequest) =
        apiService.postForgetPassword(request)

    suspend fun postUpdatePassword(request: UpdatePasswordRequest) =
        apiService.postUpdatePassword(request)

    suspend fun checkSession() = apiService.checkSession()

    suspend fun getProfile() = apiService.getProfile()

    suspend fun postEditProfile(request: ProfileEditRequest) =
        apiService.postEditProfile(request)

    //analysis
    suspend fun postPredict(image: MultipartBody.Part) = apiService.postPredict(image)

    //community
    suspend fun getCommunities() = apiService.getCommunities()

    suspend fun getDetailCommunity(id: Int) = apiService.getDetailCommunity(id)

    suspend fun createCommunity(request: CommunityCreateRequest) =
        apiService.createCommunity(request)

    suspend fun joinCommunity(request: CommunityJoinRequest) =
        apiService.joinCommunity(request)

}