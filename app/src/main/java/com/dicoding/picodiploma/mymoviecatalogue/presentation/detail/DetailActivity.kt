package com.dicoding.picodiploma.mymoviecatalogue.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.mymoviecatalogue.databinding.ContentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val extras = intent.extras
        if (extras != null) {

            binding.progressBar.visibility = View.VISIBLE

            val id = extras.getInt(EXTRA_ID, 0)
            when (extras.getInt(EXTRA_FILM)) {
                0 -> {
                    detailViewModel.setSelectedId(id)
                    detailViewModel.getMovie.observe(this, { movie ->
                        movie.data?.let { populateMovie(it) }
                        binding.collapsingToolbar.title = movie.data?.title
                        binding.progressBar.visibility = View.GONE
                        val state = movie.data?.isFavorite
                        if (state != null) {
                            isFavorite = state
                            setFavorite(isFavorite)
                        }
                    })
                    binding.fabFav.setOnClickListener {
                        if (!isFavorite) {
                            detailViewModel.setMovieFavorite()
                            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
                        } else {
                            detailViewModel.setMovieFavorite()
                            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                1 -> {
                    detailViewModel.setSelectedId(id)
                    detailViewModel.getTvShow.observe(this, { tvShow ->
                        tvShow.data?.let { populateTvShow(it) }
                        binding.collapsingToolbar.title = tvShow.data?.title
                        binding.progressBar.visibility = View.GONE
                        val state = tvShow.data?.isFavorite
                        if (state != null) {
                            isFavorite = state
                            setFavorite(isFavorite)
                        }
                    })
                    binding.fabFav.setOnClickListener {
                        if (!isFavorite) {
                            detailViewModel.setTvShowFavorite()
                            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
                        } else {
                            detailViewModel.setTvShowFavorite()
                            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun populateMovie(movieEntity: Movie) {
        detailContentBinding.apply {
            textTitle.text = movieEntity.title
            if (movieEntity.tagline!!.isEmpty()) {
                textTagline.text = "-"
            } else {
                textTagline.text = movieEntity.tagline
            }
            textDate.text = movieEntity.date
            textStatus.text = movieEntity.status
            textGenre.text = movieEntity.genre
            textDescription.text = movieEntity.description
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movieEntity.imagePath}")
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imagePoster)
    }

    private fun populateTvShow(tvShowEntity: TvShow) {
        detailContentBinding.apply {
            textTitle.text = tvShowEntity.title
            if (tvShowEntity.tagline!!.isEmpty()) {
                textTagline.text = "-"
            } else {
                textTagline.text = tvShowEntity.tagline
            }
            textDate.text = tvShowEntity.date
            textStatus.text = tvShowEntity.status
            textGenre.text = tvShowEntity.genre
            textDescription.text = tvShowEntity.description
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${tvShowEntity.imagePath}")
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imagePoster)
    }

    private fun setFavorite(state: Boolean) {
        if (!state) {
            binding.fabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.fabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}