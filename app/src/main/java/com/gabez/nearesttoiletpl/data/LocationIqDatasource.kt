package com.gabez.nearesttoiletpl.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.api.location_iq.LocationIqInterface
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import com.gabez.nearesttoiletpl.ui.StartActivity
import com.gabez.nearesttoiletpl.ui.fragments.splash.SplashFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationIqDatasource constructor() : UserCountryDatasource {

    private val apiInterface: LocationIqInterface = LocationIqInterface.create()

    override suspend fun getUserLocationCountry(lat: String, lon: String) {
        val apiCall: Call<String> = apiInterface.getCurrentCountry(
            lat,
            lon
        )

        apiCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    Log.e("API_ERROR", response.body()!!.toString())

                    try {
                        val jsonObj = JSONObject(response.body()!!)
                        val address = jsonObj.getJSONObject("address")
                        val country = address.getString("country")

                        SplashFragment.processUserCountry(
                            ApiResponse(
                                ApiResponseStatus.OK,
                                country,
                                null
                            ), StartActivity.startActivityContext
                        )

                    } catch (e: Exception) {

                        SplashFragment.processUserCountry(
                            ApiResponse(
                                ApiResponseStatus.ERROR,
                                null,
                                e.message
                            ), StartActivity.startActivityContext
                        )
                    }
                } else {
                    Log.e("API_ERROR", response.message())

                    SplashFragment.processUserCountry(
                        (ApiResponse(ApiResponseStatus.NOT_OK, null, response.message())),
                        StartActivity.startActivityContext
                    )

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("API_ERROR", t.message + " error")

                SplashFragment.processUserCountry(
                    ApiResponse(ApiResponseStatus.NOT_OK, null, t.message), StartActivity.startActivityContext
                )
            }

        })
    }
    /*
        liveData {

            val apiCall: Call<String> = apiInterface.getCurrentCountry(
                lat,
                lon
            )

            Log.e("URL", apiCall.request().url().encodedPath())

            apiCall.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Log.e("API_ERROR", response.body()!!.toString())
                        Log.e("API_ERROR", "lat: $lat, lon: $lon")

                        try {
                            val jsonObj = JSONObject(response.body()!!)
                            val address = jsonObj.getJSONObject("address")
                            val country = address.getString("country")

                            SplashFragment.processUserCountry(
                                ApiResponse(
                                    ApiResponseStatus.OK,
                                    response.body()!!,
                                    country
                                )
                            )

                            GlobalScope.launch {
                                emit(
                                    ApiResponse(
                                        ApiResponseStatus.OK,
                                        response.body()!!,
                                        country
                                    )
                                )
                            }

                        } catch (e: Exception) {

                            SplashFragment.processUserCountry(
                                ApiResponse(
                                    ApiResponseStatus.ERROR,
                                    null,
                                    e.message
                                )
                            )

                            GlobalScope.launch{emit(
                                ApiResponse(
                                    ApiResponseStatus.ERROR,
                                    null,
                                    e.message
                                )
                            )}
                        }
                    } else {
                        Log.e("API_ERROR", response.message())

                        SplashFragment.processUserCountry(
                            (ApiResponse(ApiResponseStatus.NOT_OK, null, response.message()))
                        )

                        GlobalScope.launch{emit(ApiResponse(ApiResponseStatus.NOT_OK, null, response.message()))}
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("API_ERROR", t.message+" error")

                    SplashFragment.processUserCountry(
                        ApiResponse(ApiResponseStatus.NOT_OK, null, t.message)
                    )

                    GlobalScope.launch{emit(ApiResponse(ApiResponseStatus.NOT_OK, null, t.message))}
                }

            })

        }
        */

    companion object {
        fun instance(): LocationIqDatasource = LocationIqDatasource()
    }
}