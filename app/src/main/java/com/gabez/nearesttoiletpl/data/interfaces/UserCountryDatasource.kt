package com.gabez.nearesttoiletpl.data.interfaces

import retrofit2.Response

interface UserCountryDatasource {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
}