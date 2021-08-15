package com.fero.submission1jetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.data.source.local.room.MovieTvShowDao
import com.fero.submission1jetpack.utils.AppExecutors

class LocalDataSource private constructor(private val movieTvShowDao: MovieTvShowDao){

    companion object{
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(movieTvShowDao: MovieTvShowDao): LocalDataSource = INSTANCE?: LocalDataSource(movieTvShowDao)
    }

    //Movie
    fun getAllMovies(): LiveData<List<MovieEntity>> = movieTvShowDao.getAllMovies()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieTvShowDao.getFavoriteMovie()

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieTvShowDao.getDetailMovie(movieId)

    fun insertMovie(movieList: List<MovieEntity>) = movieTvShowDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean){
        movie.isFavorite = favorite
        movieTvShowDao.updateFavoriteMovie(movie)
    }

    //TvShow
    fun getAllTvShows(): LiveData<List<TvShowEntity>> = movieTvShowDao.getAllTvShows()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = movieTvShowDao.getFavoriteTvShow()

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity> = movieTvShowDao.getDetailTvShow(tvShowId)

    fun insertTvShow(tvShowList: List<TvShowEntity>) = movieTvShowDao.insertTvShow(tvShowList)

    fun setFavoriteTvShow(tvShow: TvShowEntity, favorite: Boolean){
        tvShow.isFavorite = favorite
        movieTvShowDao.updateFavoriteTvShow(tvShow)
    }

    fun deleteTvShow(tvShowId: Int) {
        movieTvShowDao.deleteTvShow(tvShowId)
    }

    fun nukeTvShow() {
        movieTvShowDao.nukeTvShow()
    }

    fun deleteMovie(movieId: Int) {
        movieTvShowDao.deleteMovie(movieId)
    }

    fun nukeMovie() {
        movieTvShowDao.nukeMovie()
    }

}