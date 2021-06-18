package com.dicoding.picodiploma.mymoviecatalogue.favorite.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.favorite.databinding.FragmentFavMovieBinding
import com.dicoding.picodiploma.mymoviecatalogue.favorite.di.DaggerFavComponent
import com.dicoding.picodiploma.mymoviecatalogue.favorite.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.mymoviecatalogue.presentation.di.FavDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavMovieFragment : Fragment() {

    private lateinit var fragmentFavMovieBinding: FragmentFavMovieBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favMovieViewModel: FavMovieViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentFavMovieBinding =
            FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavMovieBinding.root
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
            val favMovieAdapter = FavMovieAdapter()
            fragmentFavMovieBinding.progressBar.visibility = View.VISIBLE
            favMovieViewModel.getFavoriteMovie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    favMovieAdapter.setMovie(movie)
                    fragmentFavMovieBinding.progressBar.visibility = View.GONE
                    favMovieAdapter.submitList(movie)
                    fragmentFavMovieBinding.favEmpty.root.visibility =
                        if (movie.isNotEmpty()) View.GONE else View.VISIBLE
                }
            })
            with(fragmentFavMovieBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
            }
        }
    }
}