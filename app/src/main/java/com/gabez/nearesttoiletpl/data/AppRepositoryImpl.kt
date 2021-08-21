package com.gabez.nearesttoiletpl.data

import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import com.gabez.nearesttoiletpl.location.SearchBoundaries
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val userCountryDatasource: UserCountryDatasource) : AppRepository {

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> =
        userCountryDatasource.getNearbyToilets(bounds)
}