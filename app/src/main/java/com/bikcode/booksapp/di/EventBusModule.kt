package com.bikcode.booksapp.di

import com.bikcode.booksapp.core.eventbus.AppEventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventBusModule {

    @Provides
    @Singleton
    fun providesEventBus(): AppEventBus = AppEventBus()
}