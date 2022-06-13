package com.bangkit.capsstonebangkit.repository

import com.bangkit.capsstonebangkit.data.api.ApiHelper
import okhttp3.MultipartBody

class AnalysisRepository(private val apiHelper: ApiHelper) {

    suspend fun postPredict(image: MultipartBody.Part) = apiHelper.postPredict(image)

}