package com.bangkit.capsstonebangkit.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.LoginRequest
import com.bangkit.capsstonebangkit.data.api.model.LoginResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<Response<LoginResponse>>>()
    val loginResponse: LiveData<Resource<Response<LoginResponse>>> get() = _loginResponse

    fun postLogin(request: LoginRequest){

        viewModelScope.launch {
            _loginResponse.postValue(Resource.loading())
            try {
                val dataLogin = repository.postLogin(request)
                val successResource = Resource.success(dataLogin)
                _loginResponse.postValue(successResource)
            }catch (exp: Exception){
                _loginResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}