package com.gabez.nearesttoiletpl.data.interfaces

import androidx.lifecycle.LiveData
import com.gabez.nearesttoiletpl.api.ApiResponse

interface UserCountryDatasource {
    suspend fun getUserLocationCountry(lat: String, lon: String)
}