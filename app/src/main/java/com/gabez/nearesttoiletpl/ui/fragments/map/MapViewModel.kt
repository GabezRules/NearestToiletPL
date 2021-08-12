package com.gabez.nearesttoiletpl.ui.fragments.map

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.api.helpers.ApiResponseHelper
import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.gabez.nearesttoiletpl.location.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MapViewModel: ViewModel() {

    private val getNearbyToiletsUsecase: GetNearbyToiletsUsecase = GetNearbyToiletsUsecase.instance()
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

    companion object {
        private var vm: MapViewModel? = null

        fun instance(): MapViewModel{
            if(vm==null)  vm = MapViewModel();
            return vm as MapViewModel;
        }
    }
}