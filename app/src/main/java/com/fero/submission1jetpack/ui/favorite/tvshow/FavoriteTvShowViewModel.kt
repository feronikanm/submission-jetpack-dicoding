package com.fero.submission1jetpack.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel (private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {

    fun getFavoriteTvShow() : LiveData<PagedList<TvShowEntity>> = movieTvShowRepository.getFavoriteTvShow()

//    lateinit var livePagedTvShowEntity : LiveData<PagedList<TvShowEntity>>
//    val onProgressView = MutableLiveData<Boolean>()
//
//
//    fun setFavoriteTvShow(data: TvShowEntity, favorite: Boolean) {
//        movieTvShowRepository.setFavoriteTvShow(data, favorite)
//    }
//
//    fun deleteTvShow(tvShowId: Int) {
//        movieTvShowRepository.deleteTvShow(tvShowId)
//    }
//
//    fun nukeTvShow() {
//        movieTvShowRepository.nukeTvShow()
//    }
//
//    fun getFavoriteTvShows() {
//        onProgressView.value = true
//        livePagedTvShowEntity = movieTvShowRepository.getFavoriteTvShow()
//        onProgressView.value = false
//    }

}