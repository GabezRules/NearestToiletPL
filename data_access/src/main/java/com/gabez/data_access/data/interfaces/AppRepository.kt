package com.gabez.data_access.data.interfaces

import com.gabez.data_access.SearchBoundaries
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Response

interface AppRepository {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
    suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String>
}