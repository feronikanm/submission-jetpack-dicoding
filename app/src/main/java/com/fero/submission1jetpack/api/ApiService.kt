package com.fero.submission1jetpack.api

import com.fero.submission1jetpack.data.source.remote.response.MovieTvResponse
import com.fero.submission1jetpack.data.source.remote.response.Movie
import com.fero.submission1jetpack.data.source.remote.response.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieTvResponse<Movie>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ):Call<Movie>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String
    ): Call<MovieTvResponse<TvShow>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ):Call<TvShow>

}