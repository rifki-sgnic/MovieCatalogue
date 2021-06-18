package com.dicoding.picodiploma.mymoviecatalogue.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.usecase.CatalogueUseCase
import com.dicoding.picodiploma.mymoviecatalogue.favorite.movie.FavMovieViewModel
import com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow.FavTvShowViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val catalogueUseCase: CatalogueUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                FavMovieViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                FavTvShowViewModel(catalogueUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: ${modelClass.name}")
        }
}