package com.bangkit.capsstonebangkit.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.ProfileEditRequest
import com.bangkit.capsstonebangkit.data.api.model.ProfileEditResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class EditProfileViewModel(private val repository: UserRepository): ViewModel() {

    private val _editProfileResponse = MutableLiveData<Resource<Response<ProfileEditResponse>>>()
    val editProfileResponse : LiveData<Resource<Response<ProfileEditResponse>>> = _editProfileResponse


    fun postEditProfile(request : ProfileEditRequest){
        viewModelScope.launch {
            _editProfileResponse.postValue(Resource.loading())
            try {
                val dataProfile = repository.postEditProfile(request)
                val successResource = Resource.success(dataProfile)
                _editProfileResponse.postValue(successResource)
            }catch (e : Exception){
                _editProfileResponse.postValue(Resource.error(e.localizedMessage))
            }
        }
    }
}