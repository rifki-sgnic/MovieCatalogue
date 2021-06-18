package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local

import androidx.paging.DataSource
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mCatalogueDao: CatalogueDao) {

    //movies
    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovies()
    fun getMovieById(movieId: Int): Flow<MovieEntity> = mCatalogueDao.getMovieById(movieId)
    suspend fun insertMovie(movie: List<MovieEntity>) = mCatalogueDao.insertMovie(movie)
    suspend fun updateMovie(movie: MovieEntity) = mCatalogueDao.updateMovie(movie)
    fun getMovieFavorite(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovieFavorite()
    suspend fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovie(movie)
    }

    //tv shows
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getTvShows()
    fun getTvShowById(tvShowId: Int): Flow<TvShowEntity> = mCatalogueDao.getTvShowById(tvShowId)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShow)
    suspend fun updateTvShow(tvShow: TvShowEntity) = mCatalogueDao.updateTvShow(tvShow)
    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowEntity> =
        mCatalogueDao.getTvShowFavorite()
    suspend fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTvShow(tvShow)
    }
}