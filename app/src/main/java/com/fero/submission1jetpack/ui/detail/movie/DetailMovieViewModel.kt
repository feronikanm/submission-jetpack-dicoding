package com.fero.submission1jetpack.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.vo.Resource

class DetailMovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int){
        this.movieId.value = movieId
    }

    var getDetailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { movieId ->
        movieTvShowRepository.getDetailMovie(movieId)
    }

    fun setFavorite(){
        val favoriteResource = getDetailMovie.value
        if(favoriteResource != null){
            val favoriteMovie = favoriteResource.data

            if (favoriteMovie != null){
                val state = !favoriteMovie.isFavorite
                movieTvShowRepository.setFavoriteMovie(favoriteMovie, state)
            }
        }
    }
}