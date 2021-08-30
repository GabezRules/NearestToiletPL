package com.gabez.locationiq_api.api

data class ApiResponse(val status: ApiResponseStatus, val data: Any?, val optionalMessage: String? = "")