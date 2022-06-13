package com.bangkit.capsstonebangkit.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.SessionResponse
import com.bangkit.capsstonebangkit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SplashViewModel(private val repository: UserRepository): ViewModel() {

    private val _sessionResponse = MutableLiveData<Resource<Response<SessionResponse>>>()
    val sessionResponse: LiveData<Resource<Response<SessionResponse>>> get() = _sessionResponse

    fun checkSession(){
        viewModelScope.launch {
            _sessionResponse.postValue(Resource.loading())
            try {
                val dataLogin = repository.checkSession()
                val successResource = Resource.success(dataLogin)
                _sessionResponse.postValue(successResource)
            }catch (exp: Exception){
                _sessionResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}