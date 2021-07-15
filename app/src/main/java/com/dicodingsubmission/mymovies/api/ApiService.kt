package com.dicodingsubmission.mymovies.api

import com.dicodingsubmission.mymovies.BuildConfig
import com.dicodingsubmission.mymovies.model.Item
import com.dicodingsubmission.mymovies.model.TvMoviesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object{
        const val API_KEY = BuildConfig.API_KEY

        fun create():ApiService{
            val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    fun getOnPlayingMovies(): Call<TvMoviesResponse>

    @GET("movie/popular?api_key=$API_KEY")
    fun getPopularMovies(): Call<TvMoviesResponse>

    @GET("tv/on_the_air?api_key=$API_KEY")
    fun getTvOnTheAir(): Call<TvMoviesResponse>

    @GET("tv/popular?api_key=$API_KEY")
    fun getPopularTV(): Call<TvMoviesResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovieDetail(@Path("movie_id") movieId: String): Call<Item>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getTVDetail(@Path("tv_id") tvId: String): Call<Item>

}