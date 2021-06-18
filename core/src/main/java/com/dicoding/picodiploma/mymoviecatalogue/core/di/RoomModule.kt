package com.dicoding.picodiploma.mymoviecatalogue.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.room.CatalogueDao
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.room.CatalogueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideCatalogueDb(@ApplicationContext context: Context): CatalogueDatabase {
        return Room.databaseBuilder(
            context,
            CatalogueDatabase::class.java,
            CatalogueDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCatalogueDao(database: CatalogueDatabase): CatalogueDao = database.catalogueDao()
}