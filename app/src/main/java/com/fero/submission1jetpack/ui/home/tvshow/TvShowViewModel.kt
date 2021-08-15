package com.fero.submission1jetpack.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.vo.Resource

class TvShowViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {

    fun getTvShow() : LiveData<Resource<List<TvShowEntity>>> = movieTvShowRepository.getAllTvShows()
}