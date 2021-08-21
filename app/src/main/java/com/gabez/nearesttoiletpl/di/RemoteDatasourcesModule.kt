package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQReverseGeocodingInterface
import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.LocationIqDatasource
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class RemoteDatasourcesModule {

    @Provides
    fun provideLocationIqDatasource(
        reverseGeocodingInterface: LocationIQReverseGeocodingInterface,
        autocompleteInterface: LocationIQAutocompleteInterface
    ): UserCountryDatasource {
        return LocationIqDatasource(reverseGeocodingInterface, autocompleteInterface)
    }
}