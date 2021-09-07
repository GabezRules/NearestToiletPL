package com.gabez.nearesttoiletpl.ui.fragments.map

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.data_access.LocationUtils
import com.gabez.data_access.data.ApiResponse
import com.gabez.data_access.data.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(val getNearbyToiletsUsecase: GetNearbyToiletsUsecase) :
    ViewModel() {

    private val nearbyToiletsLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    var currentToiletList: List<Toilet> = ArrayList()

    fun getNearbyToiletsLiveData(): LiveData<ApiResponse> = nearbyToiletsLiveData

    fun getNearbyToilets(context: Context, dinstance: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val userLocation: Location? = LocationUtils.getUserLocation(context)
            if (userLocation == null) {
                nearbyToiletsLiveData.postValue(
                    (ApiResponse(
                        ApiResponseStatus.ERROR,
                        null,
                        "Null location!"
                    ))
                )
            } else {

                val toiletList: ArrayList<Toilet> = ArrayList()

                getNearbyToiletsUsecase.invoke(
                    LocationUtils.getBoundariesForSearch(context, dinstance)
                )
                    .catch { exception ->
                        nearbyToiletsLiveData.postValue(
                            ApiResponse(
                                ApiResponseStatus.ERROR,
                                null,
                                exception.message
                            )
                        )
                    }
                    .collect { placeList ->
                    placeList.map { placeItem ->

                        toiletList.add(
                            Toilet(
                                placeItem.placeId,
                                placeItem.lat,
                                placeItem.lon,
                                placeItem.displayName
                            )
                        )
                    }

                    nearbyToiletsLiveData.postValue(
                        ApiResponse(
                            ApiResponseStatus.OK,
                            toiletList,
                            ""
                        )
                    )
                    currentToiletList = toiletList
                }
            }
        }
    }
}