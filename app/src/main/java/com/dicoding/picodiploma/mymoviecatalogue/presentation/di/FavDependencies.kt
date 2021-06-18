package com.dicoding.picodiploma.mymoviecatalogue.presentation.di

import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavDependencies {

    fun catalogueUseCase(): CatalogueUseCase
}