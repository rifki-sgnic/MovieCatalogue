package com.dicoding.picodiploma.mymoviecatalogue.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase

class FavMovieViewModel(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    val getFavoriteMovie = catalogueUseCase.getMovieFavorite().asLiveData()
}