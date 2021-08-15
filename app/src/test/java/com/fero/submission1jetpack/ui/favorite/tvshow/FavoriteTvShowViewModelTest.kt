package com.fero.submission1jetpack.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteTvShowViewModel(movieTvShowRepository)
    }

    @Test
    fun getFavoriteTvShow(){
        val dummyFav = pagedList
        Mockito.`when`(dummyFav.size).thenReturn(5)
        val favorite = MutableLiveData<PagedList<TvShowEntity>>()
        favorite.value = dummyFav

        Mockito.`when`(movieTvShowRepository.getFavoriteTvShow()).thenReturn(favorite)
        val entites = viewModel.getFavoriteTvShow().value
        verify(movieTvShowRepository).getFavoriteTvShow()
        Assert.assertNotNull(entites)
        Assert.assertEquals(5, entites?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyFav)
    }

}