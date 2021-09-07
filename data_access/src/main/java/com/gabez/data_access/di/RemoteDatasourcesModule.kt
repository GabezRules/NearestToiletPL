package com.gabez.data_access.di

import com.gabez.data_access.data.implementations.api.LocationIqDatasource
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.locationiq_api.api.LocationIQAutocompleteInterface
import com.gabez.locationiq_api.api.LocationIQReverseGeocodingInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatasourcesModule {

    @Provides
    fun provideLocationIqDatasource(): UserCountryDatasource = LocationIqDatasource()
}