package com.dicoding.picodiploma.mymoviecatalogue.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.Resource
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding as FragmentMovieBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieViewModel.getMovies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            movieAdapter.setMovie(movies.data)
                            showLoading(false)
                            movieAdapter.submitList(movies.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            fragmentMovieBinding.empty.root.visibility = View.VISIBLE
                        }
                    }
                }
            })
            fragmentMovieBinding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMovieBinding = null
    }
}