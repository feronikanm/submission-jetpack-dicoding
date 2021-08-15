package com.fero.submission1jetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fero.submission1jetpack.data.MovieTvShowRepository
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.ui.detail.movie.DetailMovieViewModel
import com.fero.submission1jetpack.utils.DataDummyMovies
import com.fero.submission1jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dataMovie = DataDummyMovies.generateDummyMovie()[0]
    private val movieId = dataMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(movieTvShowRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovies(){

        val dummy = Resource.success(DataDummyMovies.generateDummyMovie()[0])
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = dummy

        `when`(movieTvShowRepository.getDetailMovie(movieId)).thenReturn(detailMovie)

        val movieEntity = viewModel.getDetailMovie.observeForever(movieObserver)

        verify(movieTvShowRepository).getDetailMovie(movieId)
        verify(movieObserver).onChanged(dummy)

        Assert.assertNotNull(viewModel.getDetailMovie)
        Assert.assertEquals(dataMovie.movieId, viewModel.getDetailMovie.value?.data?.movieId)
        Assert.assertEquals(dataMovie.title, viewModel.getDetailMovie.value?.data?.title)
        Assert.assertEquals(dataMovie.date, viewModel.getDetailMovie.value?.data?.date)
        Assert.assertEquals(dataMovie.overview, viewModel.getDetailMovie.value?.data?.overview)
        Assert.assertEquals(dataMovie.poster, viewModel.getDetailMovie.value?.data?.poster)
    }


    @Test
    fun setFavorite(){
        val dummyFav = Resource.success(DataDummyMovies.generateDummyMovie()[0])
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyFav

        `when`(movieTvShowRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.getDetailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movie.value)
        viewModel.setFavorite()
        verify(movieTvShowRepository).setFavoriteMovie(movie.value?.data as MovieEntity, true)
    }

//    private lateinit var viewModel: DetailMovieViewModel
//    private val dataMovie = movieTvShowRepository.getDetailMovie(movie_Id = 0, Constant.API_KEY)
//    private val movieId = dataMovie.value?.id
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var movieTvShowRepository: MovieTvShowRepository
//
//    @Mock
//    private lateinit var movieObserver: Observer<Movie>
//
//    @Before
//    fun setUp(){
//        viewModel = DetailMovieViewModel(movieTvShowRepository)
//    }
//
//    @Test
//    fun getMovies(){
//
//        val detailMovie = MutableLiveData<Movie>()
//        detailMovie.value = dataMovie.value
//
//        `when`(movieTvShowRepository.getDetailMovie(movie_Id = 0, Constant.API_KEY)).thenReturn(detailMovie)
//
//        val movieEntity = viewModel.getDetailMovie().value as Movie
//        if (movieId != null) {
//            verify(movieTvShowRepository).getDetailMovie(movieId, Constant.API_KEY)
//        }
//        Assert.assertNotNull(movieEntity)
//        Assert.assertEquals(dataMovie.value?.id, movieEntity.id)
//        Assert.assertEquals(dataMovie.value?.title, movieEntity.title)
//        Assert.assertEquals(dataMovie.value?.date, movieEntity.date)
//        Assert.assertEquals(dataMovie.value?.overview, movieEntity.overview)
//        Assert.assertEquals(dataMovie.value?.poster, movieEntity.poster)
//        Assert.assertEquals(dataMovie.value?.backdrop, movieEntity.backdrop)
//
//        viewModel.getDetailMovie().observeForever(movieObserver)
//        verify(movieObserver).onChanged(dataMovie.value)
//    }
}