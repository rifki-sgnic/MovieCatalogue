package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {
    //movies
    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movies: MovieEntity)

    @Query("SELECT * FROM movieentities WHERE isFavorite = 1")
    fun getMovieFavorite(): DataSource.Factory<Int, MovieEntity>

    //tv shows
    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(movies: List<TvShowEntity>)

    @Update
    suspend fun updateTvShow(tvShows: TvShowEntity)

    @Query("SELECT * FROM tvshowentities WHERE isFavorite = 1")
    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowEntity>
}