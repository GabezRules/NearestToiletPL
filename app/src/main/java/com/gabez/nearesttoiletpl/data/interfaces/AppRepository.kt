package com.gabez.nearesttoiletpl.data.interfaces

import retrofit2.Response

interface AppRepository {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
}