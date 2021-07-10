package com.gabez.nearesttoiletpl.data

import androidx.lifecycle.LiveData
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource

class AppRepositoryImpl constructor(): AppRepository {

    private val userCountryDatasource: UserCountryDatasource = LocationIqDatasource.instance()

    override suspend fun getUserLocationCountry(lat: String, lon: String): LiveData<ApiResponse> {
        return userCountryDatasource.getUserLocationCountry(
            lat, lon
        )
    }

    companion object{
        public fun instance(): AppRepositoryImpl = AppRepositoryImpl()
    }
}