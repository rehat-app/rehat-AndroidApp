package com.bangkit.capsstonebangkit.ui.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capsstonebangkit.data.Resource
import com.bangkit.capsstonebangkit.data.api.model.PredictResponse
import com.bangkit.capsstonebangkit.repository.AnalysisRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response

class AnalysisResultViewModel(private val repository: AnalysisRepository):ViewModel() {

    private val _analysisResultResponse = MutableLiveData<Resource<Response<PredictResponse>>>()
    val analysisResultResponse: LiveData<Resource<Response<PredictResponse>>> get() = _analysisResultResponse

    fun postAnalysis(image: MultipartBody.Part){

        viewModelScope.launch {
            _analysisResultResponse.postValue(Resource.loading())
            try {
                val dataPredict = repository.postPredict(image)
                val successResource = Resource.success(dataPredict)
                _analysisResultResponse.postValue(successResource)
            }catch (exp: Exception){
                _analysisResultResponse.postValue(Resource.error(exp.localizedMessage ?: "Error occured"))
            }
        }
    }

}