package com.dicoding.picodiploma.mymoviecatalogue.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.core.databinding.ItemsMovieBinding
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.presentation.detail.DetailActivity

class FavMovieAdapter : PagedListAdapter<Movie, FavMovieAdapter.FavMovieViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var listMovie = ArrayList<Movie>()

    fun setMovie(movie: List<Movie>?) {
        if (movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class FavMovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.date
                tvItemDescription.text = movie.description
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.imagePath}")
                    .transform(RoundedCorners(4))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                ivShare.setOnClickListener {
                    val sIntent = Intent(Intent.ACTION_SEND)
                    sIntent.type = "text/plain"
                    val shareTitle = "Share This App"
                    val shareSub = "Check ${movie.title} from this App"
                    sIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
                    ivShare.context.startActivity(Intent.createChooser(sIntent, shareTitle))
                }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.movieId)
                    intent.putExtra(DetailActivity.EXTRA_FILM, 0)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}