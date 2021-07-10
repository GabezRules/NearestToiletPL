package com.gabez.nearesttoiletpl.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.nearesttoiletpl.api.ApiKeys
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.api.location_iq.LocationIqInterface
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationIqDatasource constructor() : UserCountryDatasource {

    private val apiInterface: LocationIqInterface = LocationIqInterface.create()

    override suspend fun getUserLocationCountry(lat: String, lon: String): LiveData<ApiResponse> {
        val userCountryData: MutableLiveData<ApiResponse> = MutableLiveData()
        val apiCall: Call<String> = apiInterface.getCurrentCountry(
            lat,
            lon
        )

        apiCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.e("API_ERROR", response.body()!!.toString())
                    Log.e("API_ERROR", "lat: $lat, lon: $lon")

                    try {
                        val jsonObj = JSONObject(response.body()!!)
                        val address = jsonObj.getJSONObject("address")
                        val country = address.getString("country")

                        userCountryData.value = ApiResponse(
                            ApiResponseStatus.OK,
                            response.body()!!,
                            country
                        )
                    } catch (e: Exception) {
                        userCountryData.value = ApiResponse(
                            ApiResponseStatus.ERROR,
                            null,
                            e.message
                        )
                    }
                } else {
                    userCountryData.value =
                        ApiResponse(ApiResponseStatus.NOT_OK, null, response.message())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                userCountryData.value = ApiResponse(ApiResponseStatus.ERROR, null, t.message)
            }
        })

        return userCountryData
    }

    companion object {
        fun instance(): LocationIqDatasource = LocationIqDatasource()
    }
}