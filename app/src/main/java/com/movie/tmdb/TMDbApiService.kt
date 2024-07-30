package com.movie.tmdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieResponse>
}
