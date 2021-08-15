package com.fero.submission1jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fero.submission1jetpack.data.source.local.LocalDataSource
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.data.source.remote.ApiResponse
import com.fero.submission1jetpack.data.source.remote.RemoteDataSource
import com.fero.submission1jetpack.data.source.remote.response.Movie
import com.fero.submission1jetpack.data.source.remote.response.TvShow
import com.fero.submission1jetpack.utils.AppExecutors
import com.fero.submission1jetpack.vo.Resource

class MovieTvShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTvShowDataSource {

    companion object {
        private var instance: MovieTvShowRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieTvShowRepository =
            instance ?: synchronized(this) {
                instance ?: MovieTvShowRepository(
                    remoteData,
                    localData,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {

            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                if (data != null) {
                    for (response in data) {
                        val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.date,
                            response.poster,
                            isFavorite = false
                        )
                        movieList.add(movie)
                    }
                    localDataSource.insertMovie(movieList)
                }
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movie_Id: Int): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(movie_Id)


            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                remoteDataSource.getDetailMovie(movie_Id)

            override fun saveCallResult(data: Movie) {
                TODO("Not yet implemented")
            }

        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {

        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (data != null) {
                    for (response in data) {
                        val tvShow = TvShowEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.date,
                            response.poster,
                            isFavorite = false
                        )
                        tvShowList.add(tvShow)
                    }
                    localDataSource.insertTvShow(tvShowList)
                }
            }
        }.asLiveData()
    }


    override fun getDetailTvShow(tv_id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getDetailTvShow(tv_id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> =
                remoteDataSource.getDetailTvShow(tv_id)

            override fun saveCallResult(data: TvShow) {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, favorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, favorite) }
    }

    override fun insertMovie(movie: List<MovieEntity>) {
        val runnable = {
            if (localDataSource.getAllMovies().value.isNullOrEmpty()) {
                localDataSource.insertMovie(movie)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, favorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, favorite) }
    }

    override fun insertTvShow(tvShow: List<TvShowEntity>) {
        val runnable = {
            if (localDataSource.getAllTvShows().value.isNullOrEmpty()) {
                localDataSource.insertTvShow(tvShow)
            }
        }
        appExecutors.diskIO().execute(runnable)
    }

    override fun deleteTvShow(tvShowId: Int) {
        appExecutors.diskIO().execute {
            localDataSource.deleteTvShow(tvShowId)
        }
    }

    override fun nukeTvShow() {
        appExecutors.diskIO().execute {
            localDataSource.nukeTvShow()
        }
    }

    override fun deleteMovie(movieId: Int) {
        appExecutors.diskIO().execute {
            localDataSource.deleteMovie(movieId)
        }
    }

    override fun nukeMovie() {
        appExecutors.diskIO().execute {
            localDataSource.nukeMovie()
        }
    }
}