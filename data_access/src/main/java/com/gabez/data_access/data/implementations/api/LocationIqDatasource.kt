package com.gabez.data_access.data.implementations.api

import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.data_access.SearchBoundaries
import com.gabez.locationiq_api.api.LocationIQAutocompleteInterface
import com.gabez.locationiq_api.api.LocationIQReverseGeocodingInterface
import retrofit2.Response
import javax.inject.Inject

class LocationIqDatasource: UserCountryDatasource {

    private val reverseGeocodingInterface: LocationIQReverseGeocodingInterface = LocationIQReverseGeocodingInterface.create()
    private val autocompleteInterface: LocationIQAutocompleteInterface = LocationIQAutocompleteInterface.create()

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        reverseGeocodingInterface.getCurrentCountry(lat, lon)

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> {
        return autocompleteInterface.getToiletsNearby(bounds.toString())
    }
}