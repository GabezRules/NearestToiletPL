package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.api.location_iq.LocationIqInterface
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import com.gabez.nearesttoiletpl.location.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel : ViewModel() {
    private val getUserCountryUseCase: GetUserCountryUsecase = GetUserCountryUsecase.instance()

    /*
    fun isUserInDesiredLocation(context: Context): LiveData<ApiResponse> {

        GlobalScope.launch {
            val userLocation: Location? = LocationUtils.getUserLocation(context)
            getUserCountryUseCase.invoke(
                userLocation!!.latitude.toString(),
                userLocation!!.longitude.toString()
            )
        }

        return liveData {
            viewModelScope.launch(Dispatchers.IO) {
                val userLocation: Location? = LocationUtils.getUserLocation(context)
                if (userLocation == null) {
                    emit((ApiResponse(ApiResponseStatus.ERROR, null, "Null location!")))
                } else {
                    emitSource(
                        getUserCountryUseCase.invoke(
                            userLocation.latitude.toString(),
                            userLocation.longitude.toString()
                        )
                    )
                }
            }
        }
    }
    */

    fun processsUserCountry(context: Context) {
        val userLocation: Location? = LocationUtils.getUserLocation(context)
        if (userLocation == null) {
            SplashFragment.processUserCountry(
                ApiResponse(
                    ApiResponseStatus.ERROR,
                    null,
                    "Null location!"
                ), context
            )
        } else {
            val lat = userLocation.latitude.toString()
            val lon = userLocation.longitude.toString()

            GlobalScope.launch(Dispatchers.IO) {
                getUserCountryUseCase.invoke(lat, lon)
            }
        }
    }

    companion object {
        fun instance(): SplashViewModel = SplashViewModel()
    }
}