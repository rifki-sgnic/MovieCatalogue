package com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.favorite.databinding.FragmentFavTvShowBinding
import com.dicoding.picodiploma.mymoviecatalogue.favorite.di.DaggerFavComponent
import com.dicoding.picodiploma.mymoviecatalogue.favorite.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.mymoviecatalogue.presentation.di.FavDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavTvShowFragment : Fragment() {

    private lateinit var fragmentFavTvShowBinding: FragmentFavTvShowBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favTvShowViewModel: FavTvShowViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentFavTvShowBinding =
            FragmentFavTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentFavTvShowBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavComponent.builder()
            .context(context)
            .favDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val favTvShowAdapter = FavTvShowAdapter()
            fragmentFavTvShowBinding.progressBar.visibility = View.VISIBLE
            favTvShowViewModel.getFavoriteTvShow.observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    favTvShowAdapter.setTvShow(tvShow)
                    fragmentFavTvShowBinding.progressBar.visibility = View.GONE
                    favTvShowAdapter.submitList(tvShow)
                    fragmentFavTvShowBinding.favEmpty.root.visibility =
                        if (tvShow.isNotEmpty()) View.GONE else View.VISIBLE
                }
            })
            with(fragmentFavTvShowBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTvShowAdapter
            }
        }
    }
}