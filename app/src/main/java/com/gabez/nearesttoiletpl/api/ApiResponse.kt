package com.gabez.nearesttoiletpl.api

data class ApiResponse(val status: ApiResponseStatus, val data: Any?, val optionalMessage: String? = "")