package com.gabez.nearesttoiletpl.data

import android.util.Log
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQReverseGeocodingInterface
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import com.gabez.nearesttoiletpl.location.SearchBoundaries
import retrofit2.Response

class LocationIqDatasource : UserCountryDatasource {

    private val reverseGeocodingInterface: LocationIQReverseGeocodingInterface = LocationIQReverseGeocodingInterface.create()
    private val autocompleteInterface: LocationIQAutocompleteInterface = LocationIQAutocompleteInterface.create()

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        reverseGeocodingInterface.getCurrentCountry(lat, lon)

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String>{
        return autocompleteInterface.getToiletsNearby(bounds.toString())}

    companion object {
        fun instance(): LocationIqDatasource = LocationIqDatasource()
    }
}