package com.gabez.data_access.data

data class ApiResponse(val status: ApiResponseStatus, val data: Any?, val optionalMessage: String? = "")