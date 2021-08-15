package com.fero.submission1jetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.ui.detail.tvshow.DetailTvShowViewModel
import com.fero.submission1jetpack.utils.DataDummyTvShows
import com.fero.submission1jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dataTvShow = DataDummyTvShows.generateDummyTvShow()[0]
    private val tvShowId = dataTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = DetailTvShowViewModel(movieTvShowRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShows(){

        val dummy = Resource.success(DataDummyTvShows.generateDummyTvShow()[0])
        val detailTvShow = MutableLiveData<Resource<TvShowEntity>>()
        detailTvShow.value = dummy

        `when`(movieTvShowRepository.getDetailTvShow(tvShowId)).thenReturn(detailTvShow)

        val tvShowEntity = viewModel.getDetailTvShow.observeForever(tvShowObserver)

        verify(movieTvShowRepository).getDetailTvShow(tvShowId)
        verify(tvShowObserver).onChanged(dummy)

        assertNotNull(viewModel.getDetailTvShow)
        assertEquals(dataTvShow.tvShowId, viewModel.getDetailTvShow.value?.data?.tvShowId)
        assertEquals(dataTvShow.title, viewModel.getDetailTvShow.value?.data?.title)
        assertEquals(dataTvShow.date, viewModel.getDetailTvShow.value?.data?.date)
        assertEquals(dataTvShow.overview, viewModel.getDetailTvShow.value?.data?.overview)
        assertEquals(dataTvShow.poster, viewModel.getDetailTvShow.value?.data?.poster)
//        assertEquals(dataTvShow.backdrop, tvShowEntity.backdrop)
    }

    @Test
    fun setFavorite(){
        val dummyFav = Resource.success(DataDummyTvShows.generateDummyTvShow()[0])
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyFav

        `when`(movieTvShowRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.getDetailTvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShow.value)
        viewModel.setFavorite()
        verify(movieTvShowRepository).setFavoriteTvShow(tvShow.value?.data as TvShowEntity, true)
    }

//    private lateinit var viewModel: DetailTvShowViewModel
//    private val dataTvShow = movieTvShowRepository.getDetailTvShow(tv_id = 0, Constant.API_KEY)
//    private val tvShowId = dataTvShow.value?.id
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var movieTvShowRepository: MovieTvShowRepository
//
//    @Mock
//    private lateinit var tvShowObserver: Observer<TvShow>
//
//    @Before
//    fun setUp(){
//        viewModel = DetailTvShowViewModel(movieTvShowRepository)
//    }
//
//    @Test
//    fun getTvShows(){
//
//        val detailTvShow = MutableLiveData<TvShow>()
//        detailTvShow.value = dataTvShow.value
//
//        `when`(movieTvShowRepository.getDetailTvShow(tv_id = 0, Constant.API_KEY)).thenReturn(detailTvShow)
//
//        val tvShowEntity = viewModel.getDetailTvShow().value as TvShow
//        verify(movieTvShowRepository).getDetailTvShow(tvShowId, Constant.API_KEY)
//        assertNotNull(tvShowEntity)
//        assertEquals(dataTvShow.value?.id, tvShowEntity.id)
//        assertEquals(dataTvShow.value?.title, tvShowEntity.title)
//        assertEquals(dataTvShow.value?.date, tvShowEntity.date)
//        assertEquals(dataTvShow.value?.overview, tvShowEntity.overview)
//        assertEquals(dataTvShow.value?.poster, tvShowEntity.poster)
//        assertEquals(dataTvShow.value?.backdrop, tvShowEntity.backdrop)
//    }
}