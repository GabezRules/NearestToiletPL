package com.gabez.nearesttoiletpl.api

import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiCall {
    val apiInterface: RetrofitInterface = RetrofitInterface.create()
    val apiCallLiveData: MutableLiveData<ApiResponse> = MutableLiveData()

    public fun enqueueCall(getRequest: GetRequest) {
        val apiCall: Call<JSONObject> = apiInterface.getCurrentCountry(
            Env.apiKey,
            getRequest.lat.toString(),
            getRequest.lon.toString(),
            getRequest.format
        )

        apiCall.enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>?, response: Response<JSONObject>?) {
                if (response != null && response.body() != null) {
                    apiCallLiveData.value =
                        ApiResponse(response.code(), ApiResponseStatus.OK, response.body()!!, null)
                }

                if (response != null && response.body() == null) {
                    apiCallLiveData.value = ApiResponse(
                        response.code(),
                        ApiResponseStatus.NOT_OK,
                        null,
                        response.message()
                    )
                }
            }

            override fun onFailure(call: Call<JSONObject>?, t: Throwable?) {
                apiCallLiveData.value = ApiResponse(0, ApiResponseStatus.ERROR, null, t!!.message)
            }
        })
    }
}