package com.bikcode.booksapp.di

import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.CategoryRepository
import com.bikcode.booksapp.domain.usecase.category.GetAllCategoriesUseCase
import com.bikcode.booksapp.domain.usecase.auth.DoSignUpUseCase
import com.bikcode.booksapp.domain.usecase.category.AddCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.DeleteCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.EditCategoryUseCase
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
    fun providesAddCategoryUseCase(categoryRepository: CategoryRepository): AddCategoryUseCase =
        AddCategoryUseCase(categoryRepository)

    @Provides
    @ViewModelScoped
    fun providesGetAllCategoriesUseCase(categoryRepository: CategoryRepository): GetAllCategoriesUseCase =
        GetAllCategoriesUseCase(categoryRepository)

    @Provides
    @ViewModelScoped
    fun providesEditCategoryUseCase(categoryRepository: CategoryRepository): EditCategoryUseCase =
        EditCategoryUseCase(categoryRepository)

    @Provides
    @ViewModelScoped
    fun providesDeleteCategoryUseCase(categoryRepository: CategoryRepository): DeleteCategoryUseCase =
        DeleteCategoryUseCase(categoryRepository)
}