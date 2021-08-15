package com.fero.submission1jetpack.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.databinding.ItemGridTvMovieBinding
import com.fero.submission1jetpack.ui.detail.movie.DetailMovieActivity
import com.fero.submission1jetpack.utils.Constant

class FavoriteMovieAdapter :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<MovieEntity>() {
            // MovieEntity details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldMovieEntity: MovieEntity,
                newMovieEntity: MovieEntity
            ) = oldMovieEntity.movieId == newMovieEntity.movieId

            override fun areContentsTheSame(
                oldMovieEntity: MovieEntity,
                newMovieEntity: MovieEntity
            ) = oldMovieEntity == newMovieEntity
        }
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movieEntity: MovieEntity? = getItem(position)

        // Note that "concert" is a placeholder if it's null.
        movieEntity?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemGridTvMovieBinding =
            ItemGridTvMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemGridTvMovieBinding)
    }

    inner class FavoriteMovieViewHolder(private var binding: ItemGridTvMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieEntity) {
            with(binding) {
                tvTitle.text = data.title
                tvOverview.text = data.overview

                Glide.with(itemView.context)
                    .load("${Constant.POSTER_PATH}${data.poster}")
                    .into(ivPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.movieId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}