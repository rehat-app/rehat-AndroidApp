package com.bangkit.capsstonebangkit.data.api

import com.bangkit.capsstonebangkit.data.api.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("api/auth/signup")
    suspend fun postRegister(
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<RegisterResponse>

    @POST("api/auth/signin")
    suspend fun postLogin(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/reset-password-email")
    suspend fun postForgetPassword(@Body request: ForgetPasswordRequest):
            Response<ForgetPasswordResponse>

    @POST("api/auth/update-password")
    suspend fun postUpdatePassword(@Body request: UpdatePasswordRequest):
            Response<UpdatePasswordResponse>

    @GET("api/checkToken")
    suspend fun checkSession(): Response<SessionResponse>


    @GET("api/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("api/editProfile")
    suspend fun postEditProfile(@Body request : ProfileEditRequest):
        Response<ProfileEditResponse>

    //analysis
    @Multipart
    @POST("api/predict")
    suspend fun postPredict(@Part image: MultipartBody.Part): Response<PredictResponse>

    //community
    @GET("api/communities")
    suspend fun getCommunities(): Response<CommunityResponse>

    @GET("api/dataCommunity/{id}")
    suspend fun getDetailCommunity(@Path("id") id: Int): Response<CommunityDetailResponse>


    @POST("api/createCommunity")
    suspend fun createCommunity(@Body request: CommunityCreateRequest):
            Response<CommunityCreateResponse>

    @POST("api/joinCommunity")
    suspend fun joinCommunity(@Body request: CommunityJoinRequest):
            Response<CommunityJoinResponse>



}