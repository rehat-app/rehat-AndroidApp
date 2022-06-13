package com.bangkit.capsstonebangkit.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.CommunityDetailResponse
import com.bangkit.capsstonebangkit.repository.CommunityRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityViewModel(private val repository: CommunityRepository): ViewModel() {

    private val _communityDetailResponse = MutableLiveData<Resource<Response<CommunityDetailResponse>>>()
    val communityDetailResponse: LiveData<Resource<Response<CommunityDetailResponse>>> get() = _communityDetailResponse

    fun getCommunityDetail(id: Int){

        viewModelScope.launch {
            _communityDetailResponse.postValue(Resource.loading())
            try {
                val dataCommunityDetail = repository.getDetailCommunity(id = id)
                val successResource = Resource.success(dataCommunityDetail)
                _communityDetailResponse.postValue(successResource)
            }catch (exp: Exception){
                _communityDetailResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}