package com.fero.submission1jetpack.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.utils.DataDummyMovies
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovies(){

        val dataMovies = Resource.success(DataDummyMovies.generateDummyMovie())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dataMovies

        `when`(movieTvShowRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value

        verify(movieTvShowRepository).getAllMovies()
        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dataMovies)

//        val dataMovies = movieTvShowRepository.getAllMovies(Constant.API_KEY)
//        val movies = MutableLiveData<List<Movie>>()
//        movies.value = dataMovies.value
//
//        `when`(movieTvShowRepository.getAllMovies(Constant.API_KEY)).thenReturn(movies)
//        val movieEntities = viewModel.getMovies().value
//
//        verify<MovieTvShowRepository>(movieTvShowRepository).getAllMovies(Constant.API_KEY)
//        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities?.size)
//
//        viewModel.getMovies().observeForever(observer)
//        verify(observer).onChanged(dataMovies.value)
    }
}