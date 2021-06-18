package com.dicoding.picodiploma.mymoviecatalogue.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.favorite.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val fragmentFavoriteBinding get() = _fragmentFavoriteBinding as FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())
        fragmentFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(
            fragmentFavoriteBinding.tabs,
            fragmentFavoriteBinding.viewPager
        ) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        with(fragmentFavoriteBinding.viewPager) {
            if (this.adapter != null) {
                this.adapter = null
            }
        }
        _fragmentFavoriteBinding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.title_movie, R.string.title_tv_shows)
    }
}