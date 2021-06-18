package com.dicoding.picodiploma.mymoviecatalogue.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val catalogueUseCase: CatalogueUseCase) :
    ViewModel() {

    private var id = MutableLiveData<Int>()

    fun setSelectedId(id: Int) {
        this.id.value = id
    }

    var getMovie = Transformations.switchMap(id) { id ->
        catalogueUseCase.getDetailMovies(id).asLiveData()
    }

    var getTvShow = Transformations.switchMap(id) { id ->
        catalogueUseCase.getDetailTvShows(id).asLiveData()
    }

    fun setMovieFavorite() {
        val movieResource = getMovie.value
        if (movieResource != null) {
            val movies = movieResource.data

            if (movies != null) {
                val isFavorite = !movies.isFavorite
                catalogueUseCase.setMovieFavorite(movies, isFavorite)
            }
        }
    }

    fun setTvShowFavorite() {
        val movieResource = getTvShow.value
        if (movieResource != null) {
            val tvShow = movieResource.data

            if (tvShow != null) {
                val isFavorite = !tvShow.isFavorite
                catalogueUseCase.setTvShowFavorite(tvShow, isFavorite)
            }
        }
    }
}