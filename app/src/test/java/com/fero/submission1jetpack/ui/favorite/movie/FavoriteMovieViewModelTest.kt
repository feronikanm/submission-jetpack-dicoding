package com.fero.submission1jetpack.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest{
    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteMovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getFavoriteMovie(){
        val dummyFav = pagedList
        `when`(dummyFav.size).thenReturn(5)
        val favorite = MutableLiveData<PagedList<MovieEntity>>()
        favorite.value = dummyFav

        `when`(movieTvShowRepository.getFavoriteMovie()).thenReturn(favorite)
        val entites = viewModel.getFavoriteMovie().value
        verify(movieTvShowRepository).getFavoriteMovie()
        Assert.assertNotNull(entites)
        Assert.assertEquals(5, entites?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyFav)
    }

}