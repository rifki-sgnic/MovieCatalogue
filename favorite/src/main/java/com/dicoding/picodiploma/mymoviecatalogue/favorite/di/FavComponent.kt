package com.dicoding.picodiploma.mymoviecatalogue.favorite.di

import android.content.Context
import com.dicoding.picodiploma.mymoviecatalogue.favorite.movie.FavMovieFragment
import com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow.FavTvShowFragment
import com.dicoding.picodiploma.mymoviecatalogue.presentation.di.FavDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavDependencies::class])
interface FavComponent {
    fun inject(favMovieFragment: FavMovieFragment)
    fun inject(favTvFragment: FavTvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun favDependencies(favDependencies: FavDependencies): Builder
        fun build(): FavComponent
    }
}