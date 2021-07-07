package com.gabez.nearesttoiletpl.api

import org.json.JSONObject

data class ApiResponse(val resultCode: Int, val status: ApiResponseStatus, val responseJson: JSONObject?, val optionalMessage: String?)