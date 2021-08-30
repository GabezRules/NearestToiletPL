package com.gabez.nearesttoiletpl

import android.util.Log
import com.gabez.app_database.room_database.entity.Toilet
import com.gabez.locationiq_api.api.ApiResponse
import com.gabez.locationiq_api.api.ApiResponseStatus
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object ApiResponseHelper {

    fun getApiResponseCountryFromString(data: String): ApiResponse {
        try {

            val jsonObj = JSONObject(data)
            val address = jsonObj.getJSONObject("address")
            val country = address.getString("country")

            return ApiResponse(
                ApiResponseStatus.OK,
                country,
                null
            )

        } catch (e: Exception) {

            Log.e("API_ERROR", "error processing json: " + data)

            return ApiResponse(
                ApiResponseStatus.ERROR,
                null,
                e.message
            )
        }
    }

    fun getApiResponseToiletsFromString(data: String): ApiResponse {
        val toilets: ArrayList<Toilet> = ArrayList()
        val jsonArray: JSONArray = JSONArray(data)

        try {
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i);

                val address: JSONObject = jsonObject.getJSONObject("address")

                val toilet: Toilet = Toilet(
                    placeId = jsonObject.getString("place_id"),
                    lat = jsonObject.getString("lat").toDouble(),
                    lon = jsonObject.getString("lon").toDouble(),
                    displayName = jsonObject.getString("display_name"),
                    neighbourhood = address.getString("neighbourhood"),
                    city = address.getString("city"),
                    postcode = address.getString("postcode"),
                    road = address.getString("road")
                )

                toilets.add(toilet);

            }

            return ApiResponse(ApiResponseStatus.OK, toilets, null)

        } catch (e: JSONException) {
            Log.e("API_ERROR", "error processing json: " + e.message)

            return ApiResponse(
                ApiResponseStatus.ERROR,
                null,
                e.message
            )
        }
    }
}