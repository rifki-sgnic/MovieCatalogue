package com.dicoding.picodiploma.mymoviecatalogue.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.picodiploma.mymoviecatalogue.favorite.movie.FavMovieFragment
import com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow.FavTvShowFragment

class SectionsPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvShowFragment()
            else -> Fragment()
        }
}