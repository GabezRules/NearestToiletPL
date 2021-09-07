package com.gabez.data_access.data.implementations.api

import com.gabez.data_access.Place
import com.gabez.data_access.SearchBoundaries
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.locationiq_api.api.LocationIQAutocompleteInterface
import com.gabez.locationiq_api.api.LocationIQReverseGeocodingInterface
import com.gabez.locationiq_api.responses.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationIqDatasource : UserCountryDatasource {

    private val reverseGeocodingInterface: LocationIQReverseGeocodingInterface =
        LocationIQReverseGeocodingInterface.create()
    private val autocompleteInterface: LocationIQAutocompleteInterface =
        LocationIQAutocompleteInterface.create()

    override suspend fun getUserLocationCountry(lat: String, lon: String): Flow<String> {
        return flow {
            val locationIqPlace = reverseGeocodingInterface.getPlaceByLatLon(lat, lon)

            emit(
                locationIqPlace.address.country
            )
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Flow<List<Place>> {
        return flow {
            val placesList =
                autocompleteInterface.getToiletsNearby(bounds.toString()).map { locationIqPlace ->

                    Place(
                        locationIqPlace.place_id,
                        locationIqPlace.lat,
                        locationIqPlace.lon,
                        createPlaceName(locationIqPlace.address)
                    )
                }

            emit(placesList)
        }.flowOn(Dispatchers.IO)
    }

    private fun createPlaceName(address: Address): String {
        var placeName: String = ""

        address.apply {
            placeName = "$name, $road, $city"
        }

        return placeName
    }
}