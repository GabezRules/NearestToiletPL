package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQReverseGeocodingInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInterfacesModule {

    @Provides
    fun provideAutocompleteinterface(): LocationIQAutocompleteInterface = LocationIQAutocompleteInterface.create()

    @Provides
    fun provideReverseGeocodingInterface(): LocationIQReverseGeocodingInterface = LocationIQReverseGeocodingInterface.create()
}