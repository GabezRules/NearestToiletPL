package com.gabez.nearesttoiletpl.data

import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import com.gabez.nearesttoiletpl.location.SearchBoundaries
import retrofit2.Response

class AppRepositoryImpl : AppRepository {

    private val userCountryDatasource: UserCountryDatasource = LocationIqDatasource.instance()

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> =
        userCountryDatasource.getNearbyToilets(bounds)


    companion object {
        private var appRepo: AppRepositoryImpl? = null;
        fun instance(): AppRepositoryImpl{
            if(appRepo==null) appRepo = AppRepositoryImpl();
            return appRepo as AppRepositoryImpl;
        }
    }
}