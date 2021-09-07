package com.gabez.data_access.data.implementations

import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.data_access.SearchBoundaries
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val userCountryDatasource: UserCountryDatasource) : AppRepository {

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> =
        userCountryDatasource.getNearbyToilets(bounds)
}