package com.gabez.nearesttoiletpl.data

import com.gabez.nearesttoiletpl.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQReverseGeocodingInterface
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import com.gabez.nearesttoiletpl.location.SearchBoundaries
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