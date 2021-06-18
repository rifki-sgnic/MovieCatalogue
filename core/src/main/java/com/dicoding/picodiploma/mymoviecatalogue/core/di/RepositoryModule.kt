package com.dicoding.picodiploma.mymoviecatalogue.core.di

import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.CatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.repository.ICatalogueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RetrofitModule::class, RoomModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(catalogueRepository: CatalogueRepository): ICatalogueRepository
}