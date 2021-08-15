package com.fero.submission1jetpack.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.vo.Resource

class MovieViewModel (private val movieTvShowRepository: MovieTvShowRepository): ViewModel() {

    fun getMovies() : LiveData<Resource<List<MovieEntity>>> = movieTvShowRepository.getAllMovies()
}