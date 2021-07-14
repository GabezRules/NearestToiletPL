package com.gabez.nearesttoiletpl.data

import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import retrofit2.Response

class AppRepositoryImpl : AppRepository {

    private val userCountryDatasource: UserCountryDatasource = LocationIqDatasource.instance()

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )


    companion object {
        fun instance(): AppRepositoryImpl = AppRepositoryImpl()
    }
}