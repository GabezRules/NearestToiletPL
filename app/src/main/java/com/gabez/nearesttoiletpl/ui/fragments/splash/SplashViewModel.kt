package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.location.Location
import androidx.lifecycle.*
import com.gabez.data_access.LocationUtils
import com.gabez.data_access.data.ApiResponse
import com.gabez.data_access.data.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(val getUserCountryUseCase: GetUserCountryUsecase) :
    ViewModel() {

    private val userLocationLiveData: MutableLiveData<ApiResponse> = MutableLiveData()

    fun getUserLocationLiveData(): LiveData<ApiResponse> = userLocationLiveData

    fun isUserInDesiredLocation(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val userLocation: Location? = LocationUtils.getUserLocation(context)
            if (userLocation == null) {
                userLocationLiveData.postValue(
                    (ApiResponse(
                        ApiResponseStatus.ERROR,
                        null,
                        "Null location!"
                    ))
                )
            } else {
                getUserCountryUseCase.invoke(
                    userLocation.latitude.toString(),
                    userLocation.longitude.toString()
                )
                    .catch { exception ->
                        userLocationLiveData.postValue(
                            ApiResponse(
                                ApiResponseStatus.ERROR,
                                null,
                                exception.message
                            )
                        )
                    }
                    .collect { country ->
                        userLocationLiveData.postValue(
                            ApiResponse(
                                ApiResponseStatus.OK,
                                country,
                                null
                            )
                        )
                    }

            }
        }
    }
}