package com.fero.submission1jetpack.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = movieTvShowRepository.getFavoriteMovie()

//    lateinit var livePagedMovieEntity : LiveData<PagedList<MovieEntity>>
//    val onProgressView = MutableLiveData<Boolean>()
//
//    fun setFavoriteMovie(data: MovieEntity, favorite: Boolean) {
//        movieTvShowRepository.setFavoriteMovie(data, favorite)
//    }
//
//    fun deleteMovie(movieId: Int) {
//        movieTvShowRepository.deleteMovie(movieId)
//    }
//
//    fun nukeMovie() {
//        movieTvShowRepository.nukeMovie()
//    }
//
//    fun getFavoriteMovies() {
//        onProgressView.value = true
//        livePagedMovieEntity = movieTvShowRepository.getFavoriteMovie()
//        onProgressView.value = false
//    }
}