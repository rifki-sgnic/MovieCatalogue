package com.dicoding.picodiploma.mymoviecatalogue.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.core.databinding.ItemsTvShowBinding
import com.dicoding.picodiploma.mymoviecatalogue.R
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow
import com.dicoding.picodiploma.mymoviecatalogue.presentation.detail.DetailActivity

class FavTvShowAdapter : PagedListAdapter<TvShow, FavTvShowAdapter.FavTvShowViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var listTvShow = ArrayList<TvShow>()

    fun setTvShow(tvShow: List<TvShow>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavTvShowViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavTvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class FavTvShowViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemDate.text = tvShow.date
                tvItemDescription.text = tvShow.description
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${tvShow.imagePath}")
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
                    val shareSub = "Check ${tvShow.title} from this App"
                    sIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
                    ivShare.context.startActivity(Intent.createChooser(sIntent, shareTitle))
                }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.tvShowId)
                    intent.putExtra(DetailActivity.EXTRA_FILM, 1)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}