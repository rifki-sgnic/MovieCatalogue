package com.dicoding.picodiploma.mymoviecatalogue.presentation.di

import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueInteractor
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatalogueUseCase(catalogueRepository: CatalogueRepository): CatalogueUseCase =
        CatalogueInteractor(catalogueRepository)
}