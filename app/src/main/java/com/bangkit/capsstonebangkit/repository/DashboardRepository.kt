package com.bangkit.capsstonebangkit.repository

import com.bangkit.capsstonebangkit.data.api.ApiHelper

class DashboardRepository(private val apiHelper: ApiHelper) {

    suspend fun getCommunities() = apiHelper.getCommunities()

    suspend fun getProfile() = apiHelper.getProfile()

}