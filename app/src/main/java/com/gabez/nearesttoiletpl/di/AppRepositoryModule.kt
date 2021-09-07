package com.gabez.nearesttoiletpl.di

import com.gabez.data_access.data.implementations.AppRepositoryImpl
import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppRepositoryModule {

    @Provides
    fun provideAppRepository(userCountryDatasource: UserCountryDatasource): AppRepository{
        return AppRepositoryImpl(userCountryDatasource)
    }
}