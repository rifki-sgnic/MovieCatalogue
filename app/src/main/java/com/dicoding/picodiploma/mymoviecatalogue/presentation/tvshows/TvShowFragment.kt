package com.dicoding.picodiploma.mymoviecatalogue.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.Resource
import com.dicoding.picodiploma.mymoviecatalogue.databinding.FragmentTvShowsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowsBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding as FragmentTvShowsBinding

    private val tvShowViewModel: TvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvShowBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()
            tvShowViewModel.getTvShow.observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            tvShowAdapter.setTvShow(tvShow.data)
                            showLoading(false)
                            tvShowAdapter.submitList(tvShow.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            fragmentTvShowBinding.empty.root.visibility = View.VISIBLE
                            fragmentTvShowBinding.empty.tvError.text =
                                tvShow.message ?: getString(R.string.error)
                        }
                    }
                }
            })
            fragmentTvShowBinding.rvTvShows.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvShowBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }
}