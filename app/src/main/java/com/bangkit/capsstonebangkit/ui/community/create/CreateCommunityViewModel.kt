package com.bangkit.capsstonebangkit.ui.community.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.CommunityCreateRequest
import com.bangkit.capsstonebangkit.data.api.model.CommunityCreateResponse
import com.bangkit.capsstonebangkit.data.api.model.CommunityJoinRequest
import com.bangkit.capsstonebangkit.data.api.model.CommunityJoinResponse
import com.bangkit.capsstonebangkit.repository.CommunityRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CreateCommunityViewModel(private val repository: CommunityRepository): ViewModel() {

    private val _joinCommunityResponse = MutableLiveData<Resource<Response<CommunityJoinResponse>>>()
    val joinCommunityResponse: LiveData<Resource<Response<CommunityJoinResponse>>> get() = _joinCommunityResponse

    fun joinCommunity(request: CommunityJoinRequest){

        viewModelScope.launch {
            _joinCommunityResponse.postValue(Resource.loading())
            try {
                val dataJoinCommunity = repository.joinCommunity(request = request)
                val successResource = Resource.success(dataJoinCommunity)
                _joinCommunityResponse.postValue(successResource)
            }catch (exp: Exception){
                _joinCommunityResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

    private val _createCommunityResponse = MutableLiveData<Resource<Response<CommunityCreateResponse>>>()
    val createCommunityResponse: LiveData<Resource<Response<CommunityCreateResponse>>> get() = _createCommunityResponse

    fun createCommunity(request: CommunityCreateRequest){

        viewModelScope.launch {
            _createCommunityResponse.postValue(Resource.loading())
            try {
                val dataProfile = repository.createCommunity(request = request)
                val successResource = Resource.success(dataProfile)
                _createCommunityResponse.postValue(successResource)
            }catch (exp: Exception){
                _createCommunityResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}