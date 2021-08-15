package com.fero.submission1jetpack.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.utils.DataDummyTvShows
import com.fero.submission1jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<TvShowEntity>>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieTvShowRepository)
    }

    @Test
    fun getTvShow(){

        val dataTvShows = Resource.success(DataDummyTvShows.generateDummyTvShow())
        val tvShows = MutableLiveData<Resource<List<TvShowEntity>>>()
        tvShows.value = dataTvShows

        `when`(movieTvShowRepository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShow().value

        verify(movieTvShowRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
//        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dataTvShows)

//        val dataTvShows = movieTvShowRepository.getAllTvShows(Constant.API_KEY)
//        val tvShows = MutableLiveData<List<TvShow>>()
//        tvShows.value = dataTvShows.value
//
//        `when`(movieTvShowRepository.getAllTvShows(Constant.API_KEY)).thenReturn(tvShows)
//        val tvShowEntities = viewModel.getTvShow().value
//
//        verify<MovieTvShowRepository>(movieTvShowRepository).getAllTvShows(Constant.API_KEY)
//        assertNotNull(tvShowEntities)
//        assertEquals(10, tvShowEntities?.size)
//
//        viewModel.getTvShow().observeForever(observer)
//        verify(observer).onChanged(dataTvShows.value)
    }
}