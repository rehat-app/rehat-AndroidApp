package com.bangkit.capsstonebangkit.repository

import com.bangkit.capsstonebangkit.data.api.ApiHelper
import com.bangkit.capsstonebangkit.data.api.model.ForgetPasswordRequest
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.data.api.model.ProfileEditRequest
import com.bangkit.capsstonebangkit.data.api.model.UpdatePasswordRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun postRegister(username: RequestBody,
                             email: RequestBody,
                             password: RequestBody,
                             image: MultipartBody.Part) = apiHelper.postRegister(username,
                                                                                email,
                                                                                password,
                                                                                image)

    suspend fun postLogin(request: LoginRequest) = apiHelper.postLogin(request)

    suspend fun postForgetPassword(request: ForgetPasswordRequest) =
        apiHelper.postForgetPassword(request)

    suspend fun postUpdatePassword(request: UpdatePasswordRequest) =
        apiHelper.postUpdatePassword(request)

    suspend fun checkSession() = apiHelper.checkSession()

    suspend fun getProfile() = apiHelper.getProfile()

    suspend fun postEditProfile(request : ProfileEditRequest) =
        apiHelper.postEditProfile(request)

}