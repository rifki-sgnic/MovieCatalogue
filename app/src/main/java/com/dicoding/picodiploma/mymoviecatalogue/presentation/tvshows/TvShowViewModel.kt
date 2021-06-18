package com.dicoding.picodiploma.mymoviecatalogue.presentation.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val getTvShow = catalogueUseCase.getAllTvShows().asLiveData()
}