package com.fero.submission1jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.room.Query
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.vo.Resource

interface MovieTvShowDataSource {

    //Movie
    fun getAllMovies() : LiveData<Resource<List<MovieEntity>>>

    fun getDetailMovie(movie_Id: Int) : LiveData<Resource<MovieEntity>>

//    fun getFavoriteMovie() : LiveData<MovieEntity>
    fun getFavoriteMovie() : LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean)

    fun insertMovie(movie: List<MovieEntity>)

    //TvShow
    fun getAllTvShows() : LiveData<Resource<List<TvShowEntity>>>

    fun getDetailTvShow(tv_id: Int) : LiveData<Resource<TvShowEntity>>

//    fun getFavoriteTvShow() : LiveData<TvShowEntity>
    fun getFavoriteTvShow() : LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, favorite: Boolean)

    fun insertTvShow(tvShow: List<TvShowEntity>)

    fun deleteTvShow(tvShowId: Int)

    fun nukeTvShow()

    fun deleteMovie(movieId: Int)

    fun nukeMovie()

}