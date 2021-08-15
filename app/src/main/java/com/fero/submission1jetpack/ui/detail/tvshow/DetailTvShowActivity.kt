package com.fero.submission1jetpack.ui.detail.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.R
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.databinding.ActivityDetailTvShowBinding
import com.fero.submission1jetpack.databinding.ContentDetailBinding
import com.fero.submission1jetpack.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.fero.submission1jetpack.utils.Constant
import com.fero.submission1jetpack.viewmodel.ViewModelFactory
import com.fero.submission1jetpack.vo.Resource
import com.fero.submission1jetpack.vo.Status

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var contentBinding: ContentDetailBinding
    private lateinit var binding: ActivityDetailTvShowBinding

    companion object {
        const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentBinding = binding.detailContent
        setContentView(binding.root)

        supportActionBar?.title = "Detail TV Show"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val detailTvShowViewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]
//        val favoriteTvShowViewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

        //get data
        val tvShowId = intent.getIntExtra(EXTRA_TV_SHOW, 0)

        if(tvShowId != null){
            detailTvShowViewModel.setSelectedTvShow(tvShowId)

            detailTvShowViewModel.getDetailTvShow.observe(this, Observer { tvShow ->
                if (tvShow != null){
                    when (tvShow.status){
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.content.visibility = View.INVISIBLE
                        }
                        Status.SUCCESS -> if (tvShow.data != null){
                            binding.progressBar.visibility = View.GONE
                            binding.content.visibility = View.VISIBLE
                            loadDetailTvShow(tvShow)
                            val statusFavorite = tvShow.data.isFavorite
                            statusFavorite?. let { favorite -> setFavoriteState(favorite) }

                            binding.fabFav.setOnClickListener {
                                if (tvShow != null){
                                    if (tvShow.data.isFavorite){
                                        Toast.makeText(this, "Succes Delete Data", Toast.LENGTH_SHORT).show()
                                    }else {
                                        Toast.makeText(this, "Succes Insert To Database", Toast.LENGTH_SHORT).show()
                                    }
                                    detailTvShowViewModel.setFavorite()
                                }
                            }
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }

//        binding.progressBar.visibility = View.VISIBLE
//        binding.content.visibility = View.INVISIBLE
//
//        detailTvShowViewModel.getDetailTvShow(tvShowId).observe(this, { tvShow ->
//            binding.progressBar.visibility = View.GONE
//            binding.content.visibility = View.VISIBLE
//            tvShow.data?.let { it1 -> listTvShow(it1) }
//            tvShow.data?.let { setupButtonFav(it.isFavorite) }
//
//            favoriteTvShowViewModel.apply {
//                tvShow.data?.let { setupFavorite(this, tvShow.data, tvShowId) }
//            }
//
//        })

    }

    private fun setFavoriteState(favorite: Boolean) {
        if (favorite) {
            binding.fabFav.setImageResource(R.drawable.ic_favorite_white)
        } else {
            binding.fabFav.setImageResource(R.drawable.ic_un_favorite_white)
        }
    }

    private fun loadDetailTvShow(tvShow: Resource<TvShowEntity>) {
        if (tvShow != null){

            contentBinding.apply {
                tvTitle.text = tvShow.data?.title
                tvDate.text = tvShow.data?.date
                tvOverview.text = tvShow.data?.overview

                Glide.with(this@DetailTvShowActivity)
                    .load("${Constant.POSTER_PATH}${tvShow.data?.poster}")
                    .into(ivPoster)
            }
        }
    }

//    private fun setupFavorite(mv: FavoriteTvShowViewModel, tvShow: TvShowEntity, tvShowId: Int) {
//        binding.fabFav.setOnClickListener {
//            if (!tvShow.isFavorite) {
//                tvShow.let { it1 -> mv.setFavoriteTvShow(it1, true) }
//                setupButtonFav(true)
//                Toast.makeText(this, "Succes Insert To Database", Toast.LENGTH_SHORT).show()
//            } else {
//                tvShow.let { it1 -> mv.deleteTvShow(tvShowId) }
//                setupButtonFav(false)
//                Toast.makeText(this, "Succes Delete Data", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun setupButtonFav(favorite: Boolean) {
//        if (favorite) {
//            binding.fabFav.setImageResource(R.drawable.ic_favorite_white)
//        } else {
//            binding.fabFav.setImageResource(R.drawable.ic_un_favorite_white)
//        }
//    }
//
//    private fun listTvShow(tvShow: TvShowEntity) {
//        contentBinding.apply {
//            tvTitle.text = tvShow.title
//            tvDate.text = tvShow.date
//            tvOverview.text = tvShow.overview
//
//            Glide.with(this@DetailTvShowActivity)
//                .load("${Constant.POSTER_PATH}${tvShow.poster}")
//                .into(ivPoster)
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}