package com.gabez.nearesttoiletpl.di

import com.gabez.locationiq_api.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.locationiq_api.api.location_iq.LocationIQReverseGeocodingInterface
import com.gabez.data_access.data.implementations.api.LocationIqDatasource
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatasourcesModule {

    @Provides
    fun provideLocationIqDatasource(reverseGeocodingInterface: LocationIQReverseGeocodingInterface,
                                    autocompleteInterface: LocationIQAutocompleteInterface): UserCountryDatasource {
        return LocationIqDatasource(reverseGeocodingInterface, autocompleteInterface)
    }
}