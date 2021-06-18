package com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase

class FavTvShowViewModel(catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    val getFavoriteTvShow = catalogueUseCase.getTvShowFavorite().asLiveData()
}