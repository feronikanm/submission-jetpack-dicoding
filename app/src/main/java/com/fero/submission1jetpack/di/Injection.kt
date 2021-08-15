package com.fero.submission1jetpack.di

import android.content.Context
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.LocalDataSource
import com.fero.submission1jetpack.data.source.local.room.MovieTvShowDatabase
import com.fero.submission1jetpack.data.source.remote.RemoteDataSource
import com.fero.submission1jetpack.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context) : MovieTvShowRepository{

        val database = MovieTvShowDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieTvShowDao())
        val appExecutors = AppExecutors()

        return MovieTvShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}