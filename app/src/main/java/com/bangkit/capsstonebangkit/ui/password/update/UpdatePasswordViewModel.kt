package com.bangkit.capsstonebangkit.ui.password.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.UpdatePasswordRequest
import com.bangkit.capsstonebangkit.data.api.model.UpdatePasswordResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UpdatePasswordViewModel(private val repository: UserRepository): ViewModel() {

    private val _updatePasswordResponse = MutableLiveData<Resource<Response<UpdatePasswordResponse>>>()
    val updatePasswordResponse: LiveData<Resource<Response<UpdatePasswordResponse>>> get() = _updatePasswordResponse

    fun postUpdatePassword(request: UpdatePasswordRequest){

        viewModelScope.launch {
            _updatePasswordResponse.postValue(Resource.loading())
            try {
                val dataUpdatePassword = repository.postUpdatePassword(request)
                val successResource = Resource.success(dataUpdatePassword)
                _updatePasswordResponse.postValue(successResource)
            }catch (exp: Exception){
                _updatePasswordResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}