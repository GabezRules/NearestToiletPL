package com.gabez.data_access.data.implementations.api

import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.data_access.SearchBoundaries
import com.gabez.locationiq_api.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.locationiq_api.api.location_iq.LocationIQReverseGeocodingInterface
import retrofit2.Response
import javax.inject.Inject

class LocationIqDatasource @Inject constructor(
    val reverseGeocodingInterface: LocationIQReverseGeocodingInterface,
    val autocompleteInterface: LocationIQAutocompleteInterface
) : UserCountryDatasource {

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        reverseGeocodingInterface.getCurrentCountry(lat, lon)

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> {
        return autocompleteInterface.getToiletsNearby(bounds.toString())
    }
}