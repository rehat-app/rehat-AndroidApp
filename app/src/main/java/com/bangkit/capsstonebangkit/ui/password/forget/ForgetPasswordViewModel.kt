package com.bangkit.capsstonebangkit.ui.password.forget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.ForgetPasswordRequest
import com.bangkit.capsstonebangkit.data.api.model.ForgetPasswordResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ForgetPasswordViewModel(private val repository: UserRepository): ViewModel() {

    private val _forgetPasswordResponse = MutableLiveData<Resource<Response<ForgetPasswordResponse>>>()
    val forgetPasswordResponse: LiveData<Resource<Response<ForgetPasswordResponse>>> get() = _forgetPasswordResponse

    fun postForgetPassword(request: ForgetPasswordRequest){

        viewModelScope.launch {
            _forgetPasswordResponse.postValue(Resource.loading())
            try {
                val dataForgetPassword = repository.postForgetPassword(request)
                val successResource = Resource.success(dataForgetPassword)
                _forgetPasswordResponse.postValue(successResource)
            }catch (exp: Exception){
                _forgetPasswordResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}