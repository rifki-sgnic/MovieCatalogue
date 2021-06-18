package com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase

import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.repository.ICatalogueRepository
import javax.inject.Inject

class CatalogueInteractor @Inject constructor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    //movie
    override fun getAllMovies() = catalogueRepository.getAllMovies()
    override fun getDetailMovies(movieId: Int) = catalogueRepository.getDetailMovies(movieId)
    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        catalogueRepository.setMovieFavorite(movie, state)

    override fun getMovieFavorite() = catalogueRepository.getMovieFavorite()

    //tv show
    override fun getAllTvShows() = catalogueRepository.getAllTvShows()
    override fun getDetailTvShows(tvShowId: Int) = catalogueRepository.getDetailTvShows(tvShowId)
    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, state)

    override fun getTvShowFavorite() = catalogueRepository.getTvShowFavorite()
}