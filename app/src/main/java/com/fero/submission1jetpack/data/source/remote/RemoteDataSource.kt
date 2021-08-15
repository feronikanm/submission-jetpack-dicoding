package com.fero.submission1jetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fero.submission1jetpack.api.ApiConfig
import com.fero.submission1jetpack.data.source.remote.response.Movie
import com.fero.submission1jetpack.data.source.remote.response.MovieTvResponse
import com.fero.submission1jetpack.data.source.remote.response.TvShow
import com.fero.submission1jetpack.utils.Constant
import com.fero.submission1jetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource  {

    companion object {

        const val API_KEY = Constant.API_KEY

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovies() : LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<Movie>>>()
        ApiConfig.getApiService().getPopularMovie(API_KEY).enqueue(object : Callback<MovieTvResponse<Movie>>{
            override fun onResponse(call: Call<MovieTvResponse<Movie>>, response: Response<MovieTvResponse<Movie>>) {
//                resultMovie.value = ApiResponse.success(response.body().results)
                resultMovie.value = response.body()?.let { ApiResponse.success(it.results) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieTvResponse<Movie>>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovie
    }

    fun getAllTvShows() : LiveData<ApiResponse<List<TvShow>>>{
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShow>>>()
        ApiConfig.getApiService().getPopularTvShow(API_KEY).enqueue(object : Callback<MovieTvResponse<TvShow>> {
            override fun onResponse(call: Call<MovieTvResponse<TvShow>>, response: Response<MovieTvResponse<TvShow>>) {
                resultTvShow.value = response.body()?.let { ApiResponse.success(it.results) }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieTvResponse<TvShow>>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShow
    }



    fun getDetailMovie(movie_Id: Int) : LiveData<ApiResponse<Movie>>{
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<Movie>>()
        ApiConfig.getApiService().getDetailMovie(movie_Id, API_KEY).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                detailMovie.value = ApiResponse.success(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return detailMovie
    }

    fun getDetailTvShow(tv_id: Int) : LiveData<ApiResponse<TvShow>>{
        EspressoIdlingResource.increment()
        val detailTvShow = MutableLiveData<ApiResponse<TvShow>>()
        ApiConfig.getApiService().getDetailTvShow(tv_id, API_KEY).enqueue(object : Callback<TvShow> {
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                detailTvShow.value = ApiResponse.success(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return detailTvShow
    }
}

