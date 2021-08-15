package com.fero.submission1jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity

@Dao
interface MovieTvShowDao {

    //Movie Dao
    @Query("SELECT * FROM movieentities")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieentities where movieId = :movieId")
    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    //TvShow Dao
    @Query("SELECT * FROM tvshowentities")
    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities where isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities where tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvshow: List<TvShowEntity>)

    @Update
    fun updateFavoriteTvShow(tvshow: TvShowEntity)

    @Query("DELETE FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun deleteTvShow(tvShowId: Int)

    @Query("DELETE FROM tvshowentities")
    fun nukeTvShow()

    @Query("DELETE FROM movieentities WHERE movieId = :movieId")
    fun deleteMovie(movieId: Int)

    @Query("DELETE FROM movieentities")
    fun nukeMovie()

}