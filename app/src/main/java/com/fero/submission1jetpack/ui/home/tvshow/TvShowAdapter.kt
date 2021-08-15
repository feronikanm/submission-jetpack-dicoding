package com.fero.submission1jetpack.ui.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.databinding.ItemGridTvMovieBinding
import com.fero.submission1jetpack.ui.detail.tvshow.DetailTvShowActivity
import com.fero.submission1jetpack.utils.Constant

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShows(tvshows: List<TvShowEntity>){
        if(tvshows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemGridTvMovieBinding = ItemGridTvMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemGridTvMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listTvShow[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listTvShow.size
    }

    inner class TvShowViewHolder(private var binding: ItemGridTvMovieBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: TvShowEntity){
            with(binding){
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