package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import com.gabez.nearesttoiletpl.location.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel constructor() : ViewModel() {
    private val getUserCountryUseCase: GetUserCountryUsecase = GetUserCountryUsecase.instance()

    fun isUserInDesiredLocation(context: Context): LiveData<ApiResponse> {
        val result: MutableLiveData<ApiResponse> = MutableLiveData()
        viewModelScope.launch(Dispatchers.IO) {
            val userLocation: Location? = LocationUtils.getUserLocation(context)
            if (userLocation == null) {
                result.postValue(ApiResponse(ApiResponseStatus.ERROR, null, "Null location!"))
            } else {
                val resultLiveData: LiveData<ApiResponse> = getUserCountryUseCase.invoke(
                    userLocation.latitude.toString(),
                    userLocation.longitude.toString()
                )

                val observer: Observer<ApiResponse?> = Observer<ApiResponse?>{ response ->
                    if(response?.data != null){
                        result.postValue(response.data as ApiResponse)
                        //resultLiveData.removeObservers()
                    }
                }

                GlobalScope.launch(Dispatchers.Main) { resultLiveData.observeForever(observer) }

                if (result.value == null) Log.e("API_RES", "null")
                else Log.e("API_RES", result.value?.data.toString())
            }
        }

        return result
    }

    companion object {
        fun instance(): SplashViewModel = SplashViewModel()
    }
}