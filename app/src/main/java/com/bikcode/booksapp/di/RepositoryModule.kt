package com.bikcode.booksapp.di

import com.bikcode.booksapp.data.repository.AuthRepositoryImpl
import com.bikcode.booksapp.data.repository.CategoryRepositoryImpl
import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesAuthRepository(): AuthRepository = AuthRepositoryImpl()


    @ViewModelScoped
    @Provides
    fun providesCategoryRepository(): CategoryRepository = CategoryRepositoryImpl()
}