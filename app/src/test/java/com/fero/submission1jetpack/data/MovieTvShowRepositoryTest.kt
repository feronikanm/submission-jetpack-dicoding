package com.fero.submission1jetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fero.submission1jetpack.data.source.local.LocalDataSource
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.data.source.remote.RemoteDataSource
import com.fero.submission1jetpack.utils.*
import com.fero.submission1jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTvShowRepositoryTest {

//    private val remote = Mockito.mock(RemoteDataSource::class.java)
//    private val movieTvShowRepository = FakeMovieTvShowRepository(remote)

    private val movieResponse = DataDummyMovies.generateDummyMovie()
    private val movieId = movieResponse[0].movieId
    private val tvResponse = DataDummyTvShows.generateDummyTvShow()
    private val tvId = tvResponse[0].tvShowId
    private val movieDataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
    private val tvShowDataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieTvShowRepository = FakeMovieTvShowRepository(remote, local, appExecutors)


    @Test
    fun getAllMovies(){
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummyMovies.generateDummyMovie()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)
        val movieEntity = LiveDataTestUtil.getValue(movieTvShowRepository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun getAllTvShows(){
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummyTvShows.generateDummyTvShow()
        `when`(local.getAllTvShows()).thenReturn(dummyTvShows)
        val tvEntity = LiveDataTestUtil.getValue(movieTvShowRepository.getAllTvShows())
        verify(local).getAllTvShows()
        assertNotNull(tvEntity.data)
        assertEquals(tvResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie(){
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummyMovies.generateDummyMovie()[0]
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieTvShowRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.movieId)
        assertEquals(movieResponse[0].movieId, movieEntity.data?.movieId)
    }


    @Test
    fun getDetailTvShow(){
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = DataDummyTvShows.generateDummyTvShow()[0]
        `when`(local.getDetailTvShow(tvId)).thenReturn(dummyTvShow)

        val tvEntity = LiveDataTestUtil.getValue(movieTvShowRepository.getDetailTvShow(tvId))
        verify(local).getDetailTvShow(tvId)
        assertNotNull(tvEntity.data)
        assertNotNull(tvEntity.data?.tvShowId)
        assertEquals(tvResponse[0].tvShowId, tvEntity.data?.tvShowId)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieTvShowRepository.getFavoriteMovie()

        val entities = Resource.success(PagedListUtil.mockPagedList(DataDummyMovies.generateDummyMovie()))
        verify(local).getFavoriteMovie()
        assertNotNull(entities.data)
        assertEquals(movieResponse.size.toLong(), entities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        movieTvShowRepository.getFavoriteTvShow()

        val entities = Resource.success(PagedListUtil.mockPagedList(DataDummyTvShows.generateDummyTvShow()))
        verify(local).getFavoriteTvShow()
        assertNotNull(entities.data)
        assertEquals(movieResponse.size.toLong(), entities.data?.size?.toLong())

    }

}