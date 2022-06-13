package com.bangkit.capsstonebangkit.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.RegisterResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    private val _registerResponse = MutableLiveData<Resource<Response<RegisterResponse>>>()
    val registerResponse: LiveData<Resource<Response<RegisterResponse>>> get() = _registerResponse

    fun postRegister(username: RequestBody,
                     email: RequestBody,
                     password: RequestBody,
                     image: MultipartBody.Part){
        viewModelScope.launch {
            _registerResponse.postValue(Resource.loading())
            try {
                val dataRegister = repository.postRegister(username, email, password, image)
                val successResource = Resource.success(dataRegister)
                _registerResponse.postValue(successResource)
            }catch (exp: Exception){
                _registerResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }
}