package com.gabez.nearesttoiletpl.helpers

import android.util.Log
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import org.json.JSONObject

object ApiResponseHelper {

    fun getApiResponseFromString(data: String): ApiResponse{
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

            Log.e("API_ERROR", "error processing json: "+data)

            return ApiResponse(
                ApiResponseStatus.ERROR,
                null,
                e.message
            )
        }
    }
}