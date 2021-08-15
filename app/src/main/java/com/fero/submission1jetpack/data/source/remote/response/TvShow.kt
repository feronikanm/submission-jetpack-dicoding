package com.fero.submission1jetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(
    var id: Int = 0,
    @SerializedName("original_name") var title: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("first_air_date") var date: String? = null,
    @SerializedName("poster_path")var poster: String? = null,
    @SerializedName("backdrop_path")var backdrop: String? = null
)