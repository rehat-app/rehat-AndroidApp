package com.bangkit.capsstonebangkit.repository

import com.bangkit.capsstonebangkit.data.api.ApiHelper
import com.bangkit.capsstonebangkit.data.api.model.CommunityCreateRequest
import com.bangkit.capsstonebangkit.data.api.model.CommunityJoinRequest

class CommunityRepository(private val apiHelper: ApiHelper) {

    suspend fun getDetailCommunity(id: Int) = apiHelper.getDetailCommunity(id)

    suspend fun createCommunity(request: CommunityCreateRequest) =
        apiHelper.createCommunity(request)

    suspend fun joinCommunity(request: CommunityJoinRequest) =
        apiHelper.joinCommunity(request)

}