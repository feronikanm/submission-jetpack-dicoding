package com.fero.submission1jetpack.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.databinding.ItemGridTvMovieBinding
import com.fero.submission1jetpack.ui.detail.tvshow.DetailTvShowActivity
import com.fero.submission1jetpack.utils.Constant

class FavoriteTvShowAdapter :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.FavoriteTvShowViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<TvShowEntity>() {
            // TvShowEntity details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldTvShowEntity: TvShowEntity,
                newTvShowEntity: TvShowEntity
            ) = oldTvShowEntity.tvShowId == newTvShowEntity.tvShowId

            override fun areContentsTheSame(
                oldTvShowEntity: TvShowEntity,
                newTvShowEntity: TvShowEntity
            ) = oldTvShowEntity == newTvShowEntity
        }
    }

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        val tvShowEntity: TvShowEntity? = getItem(position)

        // Note that "concert" is a placeholder if it's null.
        tvShowEntity?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val itemGridTvMovieBinding =
            ItemGridTvMovieBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteTvShowViewHolder(itemGridTvMovieBinding)
    }

    inner class FavoriteTvShowViewHolder(private var binding: ItemGridTvMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TvShowEntity) {
            with(binding) {
                tvTitle.text = data.title
                tvOverview.text = data.overview

                Glide.with(itemView.context)
                    .load("${Constant.POSTER_PATH}${data.poster}")
                    .into(ivPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data.tvShowId)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }
}