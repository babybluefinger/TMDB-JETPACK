package com.dicodingsubmission.mymovies.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicodingsubmission.mymovies.api.ApiService
import com.dicodingsubmission.mymovies.model.Item
import com.dicodingsubmission.mymovies.model.TvMoviesResponse
import com.dicodingsubmission.mymovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            RemoteDataSource().apply { instance = this }
        }
    }

    private val apiService = ApiService.create()

    fun getMoviesOnPlaying(): LiveData<ApiResponse<List<Item>>>{
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Item>>>()
        apiService.getOnPlayingMovies().enqueue(object : Callback<TvMoviesResponse> {
            override fun onResponse(call: Call<TvMoviesResponse>, response: Response<TvMoviesResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Item>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvMoviesResponse>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }
        })
        return resultMovies
    }

    fun getMoviesPopular() : LiveData<ApiResponse<List<Item>>>{
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Item>>>()
        apiService.getPopularMovies().enqueue(object : Callback<TvMoviesResponse>{
            override fun onResponse(call: Call<TvMoviesResponse>, response: Response<TvMoviesResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Item>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvMoviesResponse>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }
        })
        return resultMovies
    }

    fun getTvOnTheAir(): LiveData<ApiResponse<List<Item>>>{
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<Item>>>()
        apiService.getTvOnTheAir().enqueue(object : Callback<TvMoviesResponse>{
            override fun onResponse(call: Call<TvMoviesResponse>, response: Response<TvMoviesResponse>) {
                resultTv.value = ApiResponse.success(response.body()?.results as List<Item>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvMoviesResponse>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }

        })
        return resultTv
    }

    fun getTvPopular(): LiveData<ApiResponse<List<Item>>>{
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<Item>>>()
        apiService.getPopularTV().enqueue(object : Callback<TvMoviesResponse>{
            override fun onResponse(call: Call<TvMoviesResponse>, response: Response<TvMoviesResponse>) {
                resultTv.value = ApiResponse.success(response.body()?.results as List<Item>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvMoviesResponse>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }

        })
        return resultTv
    }

    fun getMovieDetail(movieId : String) : LiveData<ApiResponse<Item>>{
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<Item>>()
        apiService.getMovieDetail(movieId).enqueue(object : Callback<Item>{
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                resultMovie.value = ApiResponse.success(response.body() as Item)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }

        })
        return resultMovie
    }

    fun getTVDetail(tvId : String): LiveData<ApiResponse<Item>>{
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<Item>>()
        apiService.getTVDetail(tvId).enqueue(object : Callback<Item>{
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                resultTv.value = ApiResponse.success(response.body() as Item)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.e("Remote Data Source", "something was failed ${t.message.toString()}")
            }

        })
        return resultTv
    }

}