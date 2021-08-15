package com.fero.submission1jetpack.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fero.submission1jetpack.R
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.databinding.ActivityDetailMovieBinding
import com.fero.submission1jetpack.databinding.ContentDetailBinding
import com.fero.submission1jetpack.utils.Constant
import com.fero.submission1jetpack.viewmodel.ViewModelFactory
import com.fero.submission1jetpack.vo.Resource
import com.fero.submission1jetpack.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentBinding: ContentDetailBinding
    private lateinit var binding: ActivityDetailMovieBinding


    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentBinding = binding.detailContent
        setContentView(binding.root)

        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val detailMovieViewModel =
            ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]


        //get data
        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)

        if (movieId != null) {
            detailMovieViewModel.setSelectedMovie(movieId)

            detailMovieViewModel.getDetailMovie.observe(this, Observer { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.content.visibility = View.INVISIBLE
                        }
                        Status.SUCCESS -> if (movie.data != null) {
                            binding.progressBar.visibility = View.GONE
                            binding.content.visibility = View.VISIBLE
                            loadDetailMovie(movie)
                            val statusFavorite = movie.data.isFavorite
                            statusFavorite?.let { favorite ->
                                setFavoriteState(favorite)
                            }
                            binding.fabFav.setOnClickListener {
                                if (movie != null) {
                                    if (movie.data!!.isFavorite) {
                                        Toast.makeText(
                                            this,
                                            "Succes Delete Data",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Succes Insert To Database",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    detailMovieViewModel.setFavorite()
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
    }

    private fun loadDetailMovie(movie: Resource<MovieEntity>) {
        if (movie != null) {

            contentBinding.apply {
                tvTitle.text = movie.data?.title
                tvDate.text = movie.data?.date
                tvOverview.text = movie.data?.overview

                Glide.with(this@DetailMovieActivity)
                    .load("${Constant.POSTER_PATH}${movie.data?.poster}")
                    .into(ivPoster)

            }

        }
    }

    private fun setFavoriteState(favorite: Boolean) {
        if (favorite) {
            binding.fabFav.setImageResource(R.drawable.ic_favorite_white)
        } else {
            binding.fabFav.setImageResource(R.drawable.ic_un_favorite_white)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}