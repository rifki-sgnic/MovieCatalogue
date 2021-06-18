package com.dicoding.picodiploma.mymoviecatalogue.core.domain.repository

import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.Resource
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ICatalogueRepository {
    //movies
    fun getAllMovies(): Flow<Resource<PagedList<Movie>>>
    fun getDetailMovies(movieId: Int): Flow<Resource<Movie>>
    fun setMovieFavorite(movie: Movie, state: Boolean)
    fun getMovieFavorite(): Flow<PagedList<Movie>>

    //tv shows
    fun getAllTvShows(): Flow<Resource<PagedList<TvShow>>>
    fun getDetailTvShows(tvShowId: Int): Flow<Resource<TvShow>>
    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)
    fun getTvShowFavorite(): Flow<PagedList<TvShow>>
}