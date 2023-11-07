package com.bikcode.booksapp.di

import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.CategoryRepository
import com.bikcode.booksapp.domain.usecase.GetAllCategoriesUseCase
import com.bikcode.booksapp.domain.usecase.auth.DoSignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesDoSignUpUseCase(authRepository: AuthRepository): DoSignUpUseCase =
        DoSignUpUseCase(authRepository)

    @Provides
    @ViewModelScoped
    fun providesGetAllCategoriesUseCase(categoryRepository: CategoryRepository): GetAllCategoriesUseCase =
        GetAllCategoriesUseCase(categoryRepository)
}