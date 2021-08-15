package com.fero.submission1jetpack.ui.home.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.databinding.ItemGridTvMovieBinding
import com.fero.submission1jetpack.ui.detail.movie.DetailMovieActivity
import com.fero.submission1jetpack.utils.Constant

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var listMovie = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>){
        if(movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemGridTvMovieBinding = ItemGridTvMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemGridTvMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class MovieViewHolder(private var binding: ItemGridTvMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieEntity){
            with(binding){
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