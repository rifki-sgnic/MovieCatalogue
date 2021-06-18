package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.TvShowEntity
import javax.inject.Singleton

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
@Singleton
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun catalogueDao(): CatalogueDao

    companion object {
        const val DATABASE_NAME: String = "Catalogue.db"
    }
}