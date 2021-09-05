package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.location.Location
import androidx.lifecycle.*
import com.gabez.locationiq_api.api.ApiResponse
import com.gabez.locationiq_api.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import com.gabez.nearesttoiletpl.ApiResponseHelper
import com.gabez.data_access.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(val getUserCountryUseCase: GetUserCountryUsecase) : ViewModel() {

    private val userLocationLiveData: MutableLiveData<ApiResponse> = MutableLiveData()

    fun getUserLocationLiveData(): LiveData<ApiResponse> = userLocationLiveData

    fun isUserInDesiredLocation(context: Context) {
            viewModelScope.launch(Dispatchers.IO) {
                val userLocation: Location? = LocationUtils.getUserLocation(context)
                if (userLocation == null) {
                    userLocationLiveData.postValue((ApiResponse(ApiResponseStatus.ERROR, null, "Null location!")))
                } else {
                    val response: Response<String> = getUserCountryUseCase.invoke(
                        userLocation.latitude.toString(),
                        userLocation.longitude.toString()
                    )

                    if(response.isSuccessful){
                        userLocationLiveData.postValue(ApiResponseHelper.getApiResponseCountryFromString(response.body()!!))
                    } else userLocationLiveData.postValue(ApiResponse(ApiResponseStatus.NOT_OK, null, response.message()))

                }
            }
        }
}