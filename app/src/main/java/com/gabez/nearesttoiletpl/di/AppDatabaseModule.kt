package com.gabez.nearesttoiletpl.di

import android.content.Context
import com.gabez.data_access.data.interfaces.local_database.LocalDatasource
import com.gabez.data_access.data.implementations.local_database.LocalDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    fun provideDatabaseGateway(context: Context): LocalDatasource {
        return LocalDatasourceImpl(context)
    }

}