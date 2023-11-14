package com.bikcode.booksapp.ui.screens.account.di

import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.screens.account.data.repository.AccountRepositoryImpl
import com.bikcode.booksapp.ui.screens.account.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AccountModule {

    @Provides
    @ViewModelScoped
    fun providesAccountRepository(dispatcherProvider: DispatcherProvider): AccountRepository =
        AccountRepositoryImpl(dispatcherProvider)
}