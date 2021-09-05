package com.gabez.nearesttoiletpl.ui.fragments.map

import android.content.Context
import android.location.Location
import androidx.lifecycle.*
import com.gabez.locationiq_api.api.ApiResponse
import com.gabez.locationiq_api.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.ApiResponseHelper
import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.app_database.room_database.entity.Toilet
import com.gabez.data_access.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(val getNearbyToiletsUsecase: GetNearbyToiletsUsecase):
    ViewModel() {

    private val nearbyToiletsLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    var currentToiletList: List<Toilet> = ArrayList()

    fun getNearbyToiletsLiveData(): LiveData<ApiResponse> = nearbyToiletsLiveData

    fun getNearbyToilets(context: Context, dinstance: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val userLocation: Location? = LocationUtils.getUserLocation(context)
            if (userLocation == null) {
                nearbyToiletsLiveData.postValue((ApiResponse(ApiResponseStatus.ERROR, null, "Null location!")))
            } else {
                val response: Response<String> = getNearbyToiletsUsecase.invoke(
                    LocationUtils.getBoundariesForSearch(context, dinstance)
                )

                if(response.isSuccessful){
                    nearbyToiletsLiveData.postValue(ApiResponseHelper.getApiResponseToiletsFromString(response.body()!!))
                    currentToiletList = ApiResponseHelper.getApiResponseToiletsFromString(response.body()!!).data as List<Toilet>
                } else nearbyToiletsLiveData.postValue(ApiResponse(ApiResponseStatus.NOT_OK, null, response.raw().toString()))

            }
        }
    }
}